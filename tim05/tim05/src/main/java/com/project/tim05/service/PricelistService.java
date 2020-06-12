package com.project.tim05.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.tim05.dto.AppointmentTypeDTO;
import com.project.tim05.dto.PricelistDTO;
import com.project.tim05.model.AppointmentType;
import com.project.tim05.model.Pricelist;
import com.project.tim05.repository.PricelistRepository;

@Service
public class PricelistService {

	@Autowired
	private PricelistRepository pr;
	
	@Autowired
	private AppointmentTypeService ats;

	public ArrayList<AppointmentTypeDTO> getAptTypes(Integer id) {
		ArrayList<AppointmentTypeDTO> dtos = new ArrayList<AppointmentTypeDTO>();
		ArrayList<Pricelist> pls = (ArrayList<Pricelist>) pr.findAll();
		ArrayList<AppointmentTypeDTO> all_apts = ats.getClinicAppointmentTypes(id);
		for(AppointmentTypeDTO dto : all_apts) {
			boolean found = false;
			for(Pricelist pl : pls) {
				if(dto.getName().equals(pl.getAppointmentType().getName())) {
					found = true;
				}
			}
			if(found == false) {
				dtos.add(dto);
			}
		}
		return dtos;
	}
		
	public ArrayList<PricelistDTO> getPricelists(Integer id) {
		ArrayList<PricelistDTO> dtos = new ArrayList<PricelistDTO>();
		ArrayList<Pricelist> all = (ArrayList<Pricelist>) pr.findAll();
		for(Pricelist pl : all) {
			if(pl.getClinic().getId() == id) {
				PricelistDTO dto = new PricelistDTO();
				dto.setApt_type(pl.getAppointmentType().getName());
				dto.setClinic_id(id);
				dto.setPrice(pl.getPrice());
				dtos.add(dto);
			}
		}
		return dtos;
	}

	public int deletePrice(String apt_type, Integer id) {
		int flag = 0;
		
		try {
			// Connection connection =
			// DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja",
			// "xslquaksjvvetl",
			// "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
					"");
			AppointmentTypeDTO target = new AppointmentTypeDTO();
			ArrayList<AppointmentTypeDTO> apttypes = ats.getClinicAppointmentTypes(id);
			for(AppointmentTypeDTO dto : apttypes) {
				if(dto.getName().equals(apt_type)) {
					target = dto;
				}
			}
			String query = "DELETE FROM pricelists WHERE appointment_type = ? and clinic = ?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, target.getId());
			ps.setInt(2, id);

			flag = ps.executeUpdate();

			connection.close();
			ps.close();

		} catch (SQLException e) {

			flag = 0;
		}

		return flag;
	}

	public int addPrice(@Valid PricelistDTO dto) {
		try {
			Pricelist pl = new Pricelist();
			ArrayList<AppointmentType> apttypes = (ArrayList<AppointmentType>) ats.getAptTypes();
			for(AppointmentType at : apttypes) {
				if(at.getClinic().getId() == dto.getClinic_id() &&dto.getApt_type().equals(at.getName())) {
					pl.setAppointmentType(at);
					pl.setClinic(at.getClinic());
					pl.setPrice(dto.getPrice());
				}
			}
			pr.save(pl);
			return 1;
		}catch(Exception e) {
			return 0;
		}
	}

	public int editPrice(@Valid PricelistDTO pldto) {
		int flag = 0;
		try {
			// Connection connection =
			// DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja",
			// "xslquaksjvvetl",
			// "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
					"");
			AppointmentTypeDTO target = new AppointmentTypeDTO();
			ArrayList<AppointmentTypeDTO> apttypes = ats.getClinicAppointmentTypes(pldto.getClinic_id());
			for(AppointmentTypeDTO dto : apttypes) {
				if(dto.getName().equals(pldto.getApt_type())) {
					target = dto;
				}
			}
			String query = "UPDATE pricelists set price = ? WHERE appointment_type = ? and clinic = ?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setDouble(1, pldto.getPrice());
			ps.setInt(2, target.getId());
			ps.setInt(3, pldto.getClinic_id());

			flag = ps.executeUpdate();

			connection.close();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
			flag = 0;
		}
		return flag;
	}
}
