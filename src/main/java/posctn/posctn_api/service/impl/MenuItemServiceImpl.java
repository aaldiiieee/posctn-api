package posctn.posctn_api.service.impl;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import posctn.posctn_api.dto.response.SliceResponseDto;
import posctn.posctn_api.dto.request.MenuItemListRequestDto;
import posctn.posctn_api.dto.request.MenuItemRequestDto;
import posctn.posctn_api.dto.response.MenuItemResponseDto;
import posctn.posctn_api.enums.MenuCategoryEnum;
import posctn.posctn_api.exception.NotFoundException;
import posctn.posctn_api.mapper.MenuItemMapper;
import posctn.posctn_api.model.MenuItemModel;
import posctn.posctn_api.repository.MenuItemRepository;
import posctn.posctn_api.service.MenuItemService;

import java.util.List;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final MenuItemMapper menuItemMapper;

    public MenuItemServiceImpl(MenuItemRepository menuItemRepository,
                               MenuItemMapper menuItemMapper) {
        this.menuItemRepository = menuItemRepository;
        this.menuItemMapper = menuItemMapper;
    }

    @Override
    public SliceResponseDto<MenuItemResponseDto> getMenuItems(MenuItemListRequestDto request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());

        String search = request.getSearch();
        MenuCategoryEnum category = request.getCategory();
        Boolean active = request.getActive();

        Slice<MenuItemModel> result;

        if (search != null && category != null && active != null) {
            result = menuItemRepository.findByNameContainingIgnoreCaseAndCategoryAndIsActive(
                    search, category, active, pageable);
        } else if (search != null) {
            result = menuItemRepository.findByNameContainingIgnoreCase(search, pageable);
        } else {
            result = menuItemRepository.findAll(pageable);
        }

        List<MenuItemResponseDto> items = result.getContent()
                .stream()
                .map(menuItemMapper::toDto)
                .toList();

        return SliceResponseDto.<MenuItemResponseDto>builder()
                .items(items)
                .page(result.getNumber())
                .hasNext(result.hasNext())
                .build();
    }

    @Override
    public MenuItemResponseDto createMenuItem(MenuItemRequestDto menuItemRequestDto) {
        MenuItemModel menuItemModel = menuItemMapper.toEntity(menuItemRequestDto);
        menuItemRepository.save(menuItemModel);

        return menuItemMapper.toDto(menuItemModel);
    }

    @Override
    public MenuItemResponseDto updateMenuItem(Long menuId, MenuItemRequestDto menuItemRequestDto) {
        MenuItemModel menu = menuItemRepository.findById(menuId)
                .orElseThrow(() -> new NotFoundException("Menu tidak ditemukan"));

        menu.setName(menuItemRequestDto.getName());
        menu.setPrice(menuItemRequestDto.getPrice());
        menu.setCategory(menuItemRequestDto.getCategory());
        menu.setActive(menuItemRequestDto.isActive());
        menuItemRepository.save(menu);

        return menuItemMapper.toDto(menu);
    }

    @Override
    public void deleteMenuItem(Long menuId) {
        MenuItemModel menu = menuItemRepository.findById(menuId)
                .orElseThrow(() -> new NotFoundException("Menu tidak ditemukan"));
        menuItemRepository.delete(menu);
    }

}
