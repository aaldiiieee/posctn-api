package posctn.posctn_api.mapper;

import org.springframework.stereotype.Component;
import posctn.posctn_api.dto.CustomerDto;
import posctn.posctn_api.model.CustomerModel;

@Component
public class CustomerMapper {

    public CustomerDto toDto(CustomerModel customerModel) {
        if (customerModel == null) return null;

        CustomerDto dto = new CustomerDto();
        dto.setId(customerModel.getId());
        dto.setName(customerModel.getName());
        dto.setPhone(customerModel.getPhone());

        return dto;
    }
}
