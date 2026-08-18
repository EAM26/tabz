package com.emcode.tabz.util;

import com.emcode.tabz.dto.*;
import com.emcode.tabz.model.Role;
import com.emcode.tabz.model.Shop;
import com.emcode.tabz.model.Tab;
import com.emcode.tabz.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ModelMapper {

    public User createUserEntity(UserRequest req, User loggedInUser) {
        User user = new User();
        user.setUsername(req.username());
        user.setEmail(req.email());
        if(loggedInUser == null || loggedInUser.getUserRole() != Role.ADMIN) {
            user.setUserRole(Role.USER);
        } else {
            user.setUserRole(req.userRole());
        }
        return user;
    }

    public UserResponse createUserResponse(User user) {
        List<UserTabResponse> tabs = user.getTabs().stream().map(this::createUserTabResponse).toList();
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                tabs,
                user.getUserRole()
        );
    }

    public ShopResponseCreate createShopResponse(Shop shop, String rawToken) {
        return new ShopResponseCreate(
                shop.getId(),
                shop.getName(),
                shop.getEmail(),
                rawToken,
                shop.isActive()
        );
    }

    public ShopResponse getShopResponse(Shop shop) {
        return new ShopResponse(
                shop.getId(),
                shop.getName(),
                shop.getEmail(),
                shop.getTokenHash(),
                shop.isActive(),
                shop.getTabs().stream().map(this::createShopTabResponse).toList()
        );
    }

    public Shop createShopEntity(ShopRequest request) {
        return Shop.builder()
                .name(request.name())
                .email(request.email())
                .tokenHash(request.tokenHash())
                .active(true)
                .build();
    }

    public UserTabResponse createUserTabResponse(Tab tab) {

        return new UserTabResponse(
                tab.getId(),
                tab.getShop().getId(),
                tab.getShop().getName(),
                tab.getTotalAmount(),
                tab.getCreatedAt()
        );
    }

    public ShopTabResponse createShopTabResponse(Tab tab) {
        return new ShopTabResponse(
                tab.getId(),
                tab.getTotalAmount(),
                tab.isClaimed(),
                tab.getCreatedAt()
        );
    }
}
