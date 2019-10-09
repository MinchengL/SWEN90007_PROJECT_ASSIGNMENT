package RemoteFacade;

import DTO.AdminAssembler;
import DTO.AdminDTO;
import data_mapper.AdminDataMapper;
import models.Admin;

public class AdminFacade {
	
	public AdminDTO getAdmin(int admin_id) {
		Admin admin = AdminDataMapper.search("admin_id", admin_id+"");
		AdminDTO dto = AdminAssembler.createAdminDTO(admin);
		return dto;
	}
	
	public void updateAdmin(AdminDTO adminDTO) {
		AdminAssembler.updateAdmin(adminDTO);
	}
	
	public String getAdminJson(int admin_id) {
		Admin admin = AdminDataMapper.search("admin_id", admin_id+"");
		AdminDTO dto = AdminAssembler.createAdminDTO(admin);
		return AdminDTO.serialize(dto);
	}
	
	public void updateAdminJson(String json) {
		AdminDTO adminDTO = AdminDTO.deserialize(json);
		AdminAssembler.updateAdmin(adminDTO);
	}

}
