package posctn.posctn_api.mapper;

import org.springframework.stereotype.Component;
import posctn.posctn_api.dto.request.MenuItemRequestDto;
import posctn.posctn_api.dto.response.MenuItemResponseDto;
import posctn.posctn_api.model.MenuItemModel;

@Component
public class MenuItemMapper {

    public MenuItemResponseDto toDto(MenuItemModel menuItemModel) {
        if (menuItemModel == null) return null;

        MenuItemResponseDto response = new MenuItemResponseDto();
        response.setId(menuItemModel.getId());
        response.setName(menuItemModel.getName());
        response.setPrice(menuItemModel.getPrice());
        response.setCategory(menuItemModel.getCategory());
        response.setActive(menuItemModel.isActive());
        response.setCreatedAt(menuItemModel.getCreatedAt());

        return response;
    }

    public MenuItemModel toEntity(MenuItemRequestDto request) {
        if (request == null) return null;

        MenuItemModel menuItemModel = new MenuItemModel();
        menuItemModel.setName(request.getName());
        menuItemModel.setPrice(request.getPrice());
        menuItemModel.setCategory(request.getCategory());
        menuItemModel.setActive(request.isActive());

        return menuItemModel;
    }
}
