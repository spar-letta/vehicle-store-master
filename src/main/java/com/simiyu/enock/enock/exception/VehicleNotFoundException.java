package com.simiyu.enock.enock.exception;

public class VehicleNotFoundException extends RuntimeException{
	public VehicleNotFoundException(Long id) {
		super("Employee not found "+ id);
	}
}
