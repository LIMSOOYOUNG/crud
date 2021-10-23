package com.deft.crud.member.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.deft.crud.member.model.dao.MemberMapper;
import com.deft.crud.member.model.dto.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService {
   
   private MemberMapper memberMapper;
   
   @Autowired
   public MemberServiceImpl(MemberMapper memberMapper) {
      this.memberMapper = memberMapper;
   }

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      
      MemberDTO member = memberMapper.findMemberById(username);
      
      if(member == null) {
         member = new MemberDTO();
      }
      
      List<GrantedAuthority> authorities = new ArrayList<>();

      authorities.add(new SimpleGrantedAuthority(member.getAuthority()));
      
      UserImpl user = new UserImpl(member.getEmpId(), member.getEmpPwd(), authorities);
      user.setDetails(member);
      
      return user;
   }
}