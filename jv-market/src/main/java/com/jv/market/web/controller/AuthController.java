package com.jv.market.web.controller;

import com.jv.market.domain.dto.AuthenticationRequest;
import com.jv.market.domain.dto.AuthenticationResponse;
import com.jv.market.domain.service.JvUserDetailService;
import com.jv.market.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JvUserDetailService jvUserDetailService;
    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("authenticate")
    public ResponseEntity<AuthenticationResponse> createToken(@RequestBody AuthenticationRequest authenticationRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken( authenticationRequest.getUsername(), authenticationRequest.getPassword() ));

            UserDetails userDetails = jvUserDetailService.loadUserByUsername(authenticationRequest.getUsername());
            String jwt = jwtUtil.generateToken(userDetails);

            return  new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
