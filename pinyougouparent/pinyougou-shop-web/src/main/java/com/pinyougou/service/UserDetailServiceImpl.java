package com.pinyougou.service;

import com.pinyougou.pojo.TbSeller;
import com.pinyougou.sellergoods.service.SellerService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class UserDetailServiceImpl implements UserDetailsService {

    private SellerService sellerService;

    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        List<GrantedAuthority> authentications = new ArrayList<>();
        authentications.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        TbSeller seller = sellerService.findOne(name);
        if (seller!=null){
            if (seller.getStatus().equals("1")){
                return new User(name,seller.getPassword(),authentications );
            }else {
                return null;
            }
        }else {
            return null;
        }
    }
}
