package posctn.posctn_api.service;

import posctn.posctn_api.dto.response.SliceResponseDto;
import posctn.posctn_api.dto.request.MenuItemListRequestDto;
import posctn.posctn_api.dto.request.MenuItemRequestDto;
import posctn.posctn_api.dto.response.MenuItemResponseDto;

public interface MenuItemService {

    /**
     * Get all food menus
     * @param request
     * @return
     */
    SliceResponseDto<MenuItemResponseDto> getMenuItems(MenuItemListRequestDto request);

    /**
     * Create food menu
     * @param menuItemRequestDto
     * @return
     */
    MenuItemResponseDto createMenuItem(MenuItemRequestDto menuItemRequestDto);

    /**
     * Update food menu
     * @param menuId
     * @param menuItemRequestDto
     * @return
     */
    MenuItemResponseDto updateMenuItem(Long menuId, MenuItemRequestDto menuItemRequestDto);

    /**
     * Delete food menu
     * @param menuId
     */
    void deleteMenuItem(Long menuId);
}
