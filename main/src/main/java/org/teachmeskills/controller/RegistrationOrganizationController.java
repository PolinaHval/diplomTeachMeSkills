package org.teachmeskills.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.teachmeskills.config.service.SpringSecurityUserService;
import org.teachmeskills.dto.CreateOrganizationDto;
import org.teachmeskills.model.Organization;
import org.teachmeskills.service.OrganizationService;
//import org.teachmeskills.session.AuthContext;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/regOrganization")
@RequiredArgsConstructor
public class RegistrationOrganizationController {

  private final OrganizationService organizationService;

  @GetMapping
  protected String doGet(final Model model) {
    model.addAttribute("createOrganizationDto", new CreateOrganizationDto());
    return "regOrganization";
  }

  @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  protected String createOrganization(@ModelAttribute("createOrganizationDto") @Valid final CreateOrganizationDto createOrganizationDto,
                                      final BindingResult result) {
    if (!result.hasErrors())
      organizationService.createOrganization(createOrganizationDto);
    Organization organization = organizationService.findOrganizationByUnp(createOrganizationDto.getUnp());
    final long organizationId = organization.getId();
//    Optional<Organization> organization = organizationService.getOrganizationByUnp(createOrganizationDto.getUnp());
//    if (organization.isPresent()) {
//      authContext.setOrganizationId(organization.get().getId());
//    } else {
//      return "regOrganization";
//    }
    return "redirect:/registration/" + organizationId;
  }

}
