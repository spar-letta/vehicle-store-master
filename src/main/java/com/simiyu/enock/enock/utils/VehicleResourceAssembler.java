package com.simiyu.enock.enock.utils;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import com.simiyu.enock.enock.controller.VehicleRestController;
import com.simiyu.enock.enock.model.Vehicle;
@Component
public class VehicleResourceAssembler implements ResourceAssembler<Vehicle, Resource<Vehicle>> {

	@Override
	public Resource<Vehicle> toResource(Vehicle vehicle) {
		
		return new Resource<>(vehicle,
				linkTo(methodOn(VehicleRestController.class).one(vehicle.getId())).withSelfRel(),
				linkTo(methodOn(VehicleRestController.class).all()).withRel("vehicle")
				);
	}

}
