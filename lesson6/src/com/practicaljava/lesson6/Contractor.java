package com.practicaljava.lesson6;

public class Contractor extends Person implements Payable {

	public Contractor(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean increasePay(int percent) {
		// TODO Auto-generated method stub
		if (percent >= INCREASE_CAP){
			System.out.println("Cannot do with more than 20%");
		return false;
	}
		else {
			System.out.println("Increasing salary by " + percent + "%." + getName());
			return true;
		}
				
		}
			
	}

