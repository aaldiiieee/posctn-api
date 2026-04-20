package posctn.posctn_api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import posctn.posctn_api.dto.ApiResponseDto;
import posctn.posctn_api.dto.SliceResponseDto;
import posctn.posctn_api.dto.request.MenuItemListRequestDto;
import posctn.posctn_api.dto.request.MenuItemRequestDto;
import posctn.posctn_api.dto.response.MenuItemResponseDto;
import posctn.posctn_api.service.MenuItemService;

@RestController
@RequestMapping("/api/v1/menus")
@Tag(name = "Food Menu Management System", description = "Endpoints for managing food menus")
public class MenuItemController {

    private final MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<SliceResponseDto<MenuItemResponseDto>>> getMenuItems(
            @ModelAttribute MenuItemListRequestDto request) {

        SliceResponseDto<MenuItemResponseDto> data = menuItemService.getMenuItems(request);
        return ResponseEntity.ok(new ApiResponseDto<>(200, "Data berhasil diambil", data));
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponseDto<MenuItemResponseDto>> createMenuItem(MenuItemRequestDto request) {
        MenuItemResponseDto data = menuItemService.createMenuItem(request);
        ApiResponseDto<MenuItemResponseDto> response = new ApiResponseDto<>(200,
                request.getCategory().getLabel() + " berhasil ditambahkan", data);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponseDto<MenuItemResponseDto>> updateMenuItem(
            @PathVariable Long id,
            @RequestBody MenuItemRequestDto request) {
        MenuItemResponseDto data = menuItemService.updateMenuItem(id, request);
        ApiResponseDto<MenuItemResponseDto> response = new ApiResponseDto<>(
                200, "Data berhasil diperbarui", data);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponseDto<MenuItemResponseDto>> deleteMenuItem(Long menuItemId) {
        menuItemService.deleteMenuItem(menuItemId);
        ApiResponseDto<MenuItemResponseDto> response =
                new ApiResponseDto<>(200, "Data berhasil dihapus", null);
        return ResponseEntity.ok(response);
    }
}
