package de.sukisoft.connector.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import de.sukisoft.connector.db.CountryRepository;
import de.sukisoft.connector.db.UserRepository;

/**
 * Controller for the home-dashbaord.
 *
 * @author wesselmann
 */
@Controller
public class HomeController extends AbstractViewController {

  private final CountryRepository countryRepository;
  private final UserRepository userRepository;

  @Autowired
  public HomeController(CountryRepository countryRepository, UserRepository userRepository) {
    this.countryRepository = countryRepository;
    this.userRepository = userRepository;
  }

  @GetMapping("/home")
  public String main(Model model) {
    model.addAttribute("countryCount", countryRepository.count());
    model.addAttribute("userCount", userRepository.count());
    return "home";
  }

}
