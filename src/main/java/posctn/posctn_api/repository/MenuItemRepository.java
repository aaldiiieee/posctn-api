package posctn.posctn_api.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import posctn.posctn_api.enums.MenuCategoryEnum;
import posctn.posctn_api.model.MenuItemModel;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItemModel, Long> {

    /**
     * Filter and search for food menus
     * @param name
     * @param category
     * @param active
     * @param pageable
     * @return
     */
    Slice<MenuItemModel> findByNameContainingIgnoreCaseAndCategoryAndIsActive(
            String name,
            MenuCategoryEnum category,
            boolean active,
            Pageable pageable
    );

    /**
     * Search keyword by menu name
     * @param name
     * @return
     */
    Slice<MenuItemModel> findByNameContainingIgnoreCase(String name,  Pageable pageable);
}
