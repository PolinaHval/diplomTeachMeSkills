//package org.teachmeskills.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.teachmeskills.dto.CreateUserDto;
//import org.teachmeskills.error.UserAlreadyExistException;
//import org.teachmeskills.model.Organization;
//import org.teachmeskills.service.OrganizationService;
//import org.teachmeskills.service.UserService;
//import org.teachmeskills.session.AuthContext;
//
//import javax.validation.Valid;
//
//@Controller
//@RequestMapping("/addUser")
//@RequiredArgsConstructor
//public class AddUserController {
//  private final UserService userService;
//  private final AuthContext authContext;
//  private final OrganizationService organizationService;
//
//  @GetMapping
//  protected String doGet(final Model model) {
//    model.addAttribute("createUserDto", new CreateUserDto());
//    return "addUser";
//  }
//
//  @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//  protected String addUser(@ModelAttribute("createUserDto") @Valid final CreateUserDto createUserDto,
//                           final BindingResult result) {
//    final long loggedOrganizationId = authContext.getOrganizationId();
//    final Organization organization = organizationService.getOrganizationById(loggedOrganizationId);
//    if (!result.hasErrors()) {
//      try {
//        userService.createUser(createUserDto, organization);
//        return "redirect:/users";
//      } catch (UserAlreadyExistException exception) {
//        System.out.println(exception.getMessage());
//        return "addUser";
//      }
//    } else {
//      return "addUser";
//    }
//  }
//
//}