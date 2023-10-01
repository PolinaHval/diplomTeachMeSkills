package org.teachmeskills.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.teachmeskills.dto.CreateUserDto;
import org.teachmeskills.error.UserAlreadyExistException;
import org.teachmeskills.model.Organization;
import org.teachmeskills.service.OrganizationService;
import org.teachmeskills.service.UserService;



import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationUserController {

  private final UserService userService;
//  private final AuthContext authContext;
  private final OrganizationService organizationService;

  @GetMapping("/{organizationId}")
  protected String doGet(@PathVariable("organizationId") long organizationId,final Model model) {
    Organization organization = organizationService.getOrganizationById(organizationId);
    model.addAttribute("organization", organization);
    model.addAttribute("createUserDto", new CreateUserDto());
    return "registration";
  }

  @PostMapping(path =  "/{organizationId}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  protected String createUser(@PathVariable("organizationId") long organizationId,
                              @ModelAttribute("createUserDto") @Valid final CreateUserDto createUserDto,
                              final BindingResult result) {
    final Organization organization = organizationService.getOrganizationById(organizationId);
    if (!result.hasErrors()) {
      try {
        userService.createUser(createUserDto, organization);
        return "redirect:/login";
      } catch (UserAlreadyExistException exception) {
        System.out.println(exception.getMessage());
        return "registration";
      }
    } else {
      return "registration";
    }
  }
}
