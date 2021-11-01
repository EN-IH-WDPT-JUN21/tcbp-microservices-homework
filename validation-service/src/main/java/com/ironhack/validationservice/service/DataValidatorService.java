package com.ironhack.validationservice.service;

import com.google.gson.Gson;
import com.ironhack.validationservice.dto.AccountDTO;
import com.ironhack.validationservice.dto.LeadDTO;
import com.ironhack.validationservice.proxy.AccountServiceProxy;
import com.ironhack.validationservice.proxy.LeadServiceProxy;
import com.ironhack.validationservice.proxy.OpportunityServiceProxy;
import com.ironhack.validationservice.proxy.SalesRepServiceProxy;
import org.apache.commons.lang.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class DataValidatorService {

    @Autowired
    private SalesRepServiceProxy salesRepServiceProxy;
    @Autowired
    private LeadServiceProxy leadServiceProxy;
    @Autowired
    private OpportunityServiceProxy opportunityServiceProxy;
    @Autowired
    private AccountServiceProxy accountServiceProxy;



    //Method to check if the input for the e-mail address has a correct form
    public boolean validateEmail(String input) {
        final String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    //Method to check if the input for the phone number has a correct form
    public boolean validatePhoneNumber(String input) {
        String inputWithoutSpaces = input.replaceAll("-", "");
        final String regex = "^\\+?\\d{6,15}"; // Phone number should contain 6-15 digits and can include the country code

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputWithoutSpaces);
        return matcher.matches();
    }

//    //Method to check if the input is empty
//    public boolean isEmpty(String input) {
//        return input.isBlank();
//    }
//
//
//
//    //Method to check if an account with the specific ID exists
//    public boolean accountExists(String input) {
//        List<Account> listOfAccounts = accountRepository.findAll();
//        long inputAsLong = Long.parseLong(input);
//
//        for(Account account : listOfAccounts) {
//            long accountId = account.getId();
//            if(accountId == inputAsLong) return true;
//        }
//        return false;
//    }
//
//
//    //Method to check if a lead with the same information already exists
//    public boolean isDuplicateLead(LeadObject newLeadObject) {
//        List<LeadObject> listOfLeadObjects = leadObjectRepository.findAll();
//
//        for(LeadObject leadObject : listOfLeadObjects) {
//            if(leadObject.equals(newLeadObject)) return true;
//        }
//        return false;
//    }
//
//    //Method to check if an account with the same information already exists
//    public boolean isDuplicateAccount(Account newAccount) {
//        List<Account> listOfAccounts = accountRepository.findAll();
//
//        for(Account account : listOfAccounts) {
//            if(account.equals(newAccount)) return true;
//        }
//        return false;
//    }

    //Method to check if the String contains only letters and white spaces
    public boolean containsOnlyLetters(String input) {
        final String regex = "^[ a-zA-Z]+$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    //Method to  check if the String contains only numbers, from 1 - 9999
    public boolean isNumeric(String input) {
        final String regex = "[1-9]|[1-9][0-9]|[1-9][0-9][0-9]|[1-9][0-9][0-9][0-9]";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    //Method to check if the String is an ISO country name
    public boolean validateCountryName(String countryName) {
        List<String> countryList = getCountryList()[0];
        boolean check = false;
        for(String country: countryList) {
            if(WordUtils.capitalizeFully(countryName).equals(country)) {
                check = true;
                break;
            }
        }
        return check;
    }

    // Obtain list of ISO country names
    public List[] getCountryList() {
        List<String> countryNames = new ArrayList<>();
        List<String> countryCodes = new ArrayList<>();
        Locale.setDefault(Locale.forLanguageTag("en-GB")); //set Locale for English
        String[] isoCountries = Locale.getISOCountries(); //obtain ISO country list
        for (String country : isoCountries) {
            Locale locale = new Locale("en", country);
            String countryName = locale.getDisplayCountry();
            String countryCode = locale.getCountry();
            if ( !"".equals(countryName)) {
                countryNames.add(countryName); //store country name in list
                countryCodes.add(countryCode); //store country code in list
            }
        }
        return new List[] {countryNames, countryCodes};
    }

    public boolean salesRepExists(Long id) {
        try {
            String salesRep = salesRepServiceProxy.getSalesRepById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean leadExists(Long id) {
        try {
            String lead = leadServiceProxy.getLeadById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean opportunityExists(Long id) {
        try {
            String opportunity = opportunityServiceProxy.getOpportunityById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean accountExists(Long id) {
        try {
            String account = accountServiceProxy.getAccountById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean isLeadDuplicate(LeadDTO leadDTO) {
        String allLeadsString = leadServiceProxy.getLeads();
        Gson gson = new Gson();
        String leadJSONString = gson.toJson(leadDTO);

        return allLeadsString.toLowerCase().contains(leadJSONString.toLowerCase());

    }

    public boolean isAccountDuplicate(AccountDTO accountDTO) {
        String allAccountsString = accountServiceProxy.getAllAccounts();
        Gson gson = new Gson();
        String accountJSONString = gson.toJson(accountDTO);

        return allAccountsString.toLowerCase().contains(accountJSONString.toLowerCase());
    }
}

