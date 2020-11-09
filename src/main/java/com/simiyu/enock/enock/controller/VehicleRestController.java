package com.simiyu.enock.enock.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simiyu.enock.enock.exception.VehicleNotFoundException;
import com.simiyu.enock.enock.model.Vehicle;
import com.simiyu.enock.enock.repository.VehicleRepository;
import com.simiyu.enock.enock.service.FileStorageService;
import com.simiyu.enock.enock.utils.AppConstants;
import com.simiyu.enock.enock.utils.VehicleResourceAssembler;

@CrossOrigin("*")
@RestController
public class VehicleRestController {

	ObjectMapper objectMapper = new ObjectMapper();
	
	private final VehicleRepository vehicleRepository;
	
	private final FileStorageService fileStorageService;
	
	private final VehicleResourceAssembler assembler;
	
	VehicleRestController(VehicleRepository vehicleRepository, FileStorageService fileStorageService,VehicleResourceAssembler assembler){
		this.vehicleRepository = vehicleRepository;
		this.fileStorageService = fileStorageService;
		this.assembler = assembler;
	}
	
	@GetMapping(value="/", produces="application/json")
	public Resources<Resource<Vehicle>> all(){
		List<Resource<Vehicle>> employees = vehicleRepository.findAll().stream()
				.map(assembler :: toResource)
			    .collect(Collectors.toList());		
		return new Resources<>(employees,
				linkTo(methodOn(VehicleRestController.class).all()).withSelfRel());	
	}
	
	@GetMapping(value = "/vehicle/{id}", produces = "application/json; charset=UTF-8")
	public Resource<Vehicle> one(@PathVariable Long id){		
		Vehicle veh = vehicleRepository.findById(id).orElseThrow(() -> new VehicleNotFoundException(id));		
		return assembler.toResource(veh);
	}
	
	@PostMapping(value= AppConstants.VEHICLE_URI, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	ResponseEntity<?> newVehicle(@ModelAttribute Vehicle newVehicle,
			@RequestParam(value = AppConstants.VEHICLE_JSON_PARAM,required=true) String vehJson,
			@RequestParam(value = AppConstants.VEHICLE_FILE_PARAM,required=true) MultipartFile file
			)throws URISyntaxException, IOException,JsonMappingException {
		
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(AppConstants.DOWNLOAD_PATH).path(fileName).toUriString();	
		
		newVehicle = objectMapper.readValue(vehJson, Vehicle.class);
		
		newVehicle.setPicPath(fileDownloadUri);		
		
		Resource<Vehicle> resource = assembler.toResource(vehicleRepository.save(newVehicle));		
		
		return ResponseEntity
			    .created(new URI(resource.getId().expand().getHref()))
			    .body(resource);
	}	
	@GetMapping(value=AppConstants.DOWNLOAD_URI)
	public ResponseEntity<org.springframework.core.io.Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request){
		org.springframework.core.io.Resource resource = fileStorageService.loadFileAsResource(fileName);
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());			
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		if(contentType ==null) {
			contentType = AppConstants.DEFAULT_CONTENT_TYPE;
		}
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION,
						String.format(AppConstants.FILE_DOWNLOAD_HTTP_HEADER, resource.getFilename()))
				.body(resource);
	}
	@DeleteMapping("/vehicle/{id}")
	ResponseEntity<?> deleteVehicle(@PathVariable Long id) {
		
			vehicleRepository.deleteById(id);		

	  return ResponseEntity.noContent().build();
	}
}
