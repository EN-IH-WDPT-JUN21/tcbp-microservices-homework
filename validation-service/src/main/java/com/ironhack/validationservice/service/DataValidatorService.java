package com.ironhack.validationservice;

import org.apache.commons.lang.WordUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class DataValidatorService {



    public DataValidatorService(LeadObjectRepository leadObjectRepository,
                                OpportunityRepository opportunityRepository,
                                SalesRepRepository salesRepRepository,
                                AccountRepository accountRepository) {
        this.leadObjectRepository = leadObjectRepository;
        this.opportunityRepository = opportunityRepository;
        this.salesRepRepository = salesRepRepository;
        this.accountRepository = accountRepository;
    }

    //Method to check if the input for the e-mail address has a correct form
    public static boolean validateEmail(String input) {
        final String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    //Method to check if the input for the phone number has a correct form
    public static boolean validatePhoneNumber(String input) {
        String inputWithoutSpaces = input.replaceAll("-", "");
        final String regex = "^\\+?\\d{6,15}"; // Phone number should contain 6-15 digits and can include the country code

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputWithoutSpaces);
        return matcher.matches();
    }

    //Method to check if the input is empty
    public static boolean isEmpty(String input) {
        return input.isBlank();
    }

    //Method to check if a lead with a specific ID exists
    public static boolean leadExists(String input) {
        List<LeadObject> listOfLeadObjects = leadObjectRepository.findAll();
        long inputAsLong = Long.parseLong(input);

        for(LeadObject leadObject : listOfLeadObjects) {
            long leadId = leadObject.getId();
            if(leadId == inputAsLong) return true;
        }
        return false;
    }

    //Method to check if an opportunity with the specific ID exists
    public static boolean opportunityExists(String input) {
        List<Opportunity> listOfOpportunities = opportunityRepository.findAll();
        long inputAsLong = Long.parseLong(input);

        for(Opportunity opportunity : listOfOpportunities) {
            long opportunityId = opportunity.getId();
            if(opportunityId == inputAsLong) return true;
        }
        return false;
    }

    //Method to check if an account with the specific ID exists
    public static boolean accountExists(String input) {
        List<Account> listOfAccounts = accountRepository.findAll();
        long inputAsLong = Long.parseLong(input);

        for(Account account : listOfAccounts) {
            long accountId = account.getId();
            if(accountId == inputAsLong) return true;
        }
        return false;
    }

    //Method to check if a salesRep with the specific ID exists
    public static boolean salesRepExists(String input) {
        List<SalesRep> listOfSalesReps = salesRepRepository.findAll();
        long inputAsLong = Long.parseLong(input);

        for(SalesRep salesRep : listOfSalesReps) {
            long salesRepId = salesRep.getId();
            if(salesRepId == inputAsLong) return true;
        }
        return false;
    }

    //Method to check if a lead with the same information already exists
    public static boolean isDuplicateLead(LeadObject newLeadObject) {
        List<LeadObject> listOfLeadObjects = leadObjectRepository.findAll();

        for(LeadObject leadObject : listOfLeadObjects) {
            if(leadObject.equals(newLeadObject)) return true;
        }
        return false;
    }

    //Method to check if an account with the same information already exists
    public static boolean isDuplicateAccount(Account newAccount) {
        List<Account> listOfAccounts = accountRepository.findAll();

        for(Account account : listOfAccounts) {
            if(account.equals(newAccount)) return true;
        }
        return false;
    }

    //Method to check if the String contains only letters and white spaces
    public static boolean containsOnlyLetters(String input) {
        final String regex = "^[ a-zA-Z]+$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    //Method to  check if the String contains only numbers, from 1 - 9999
    public static boolean isNumeric(String input) {
        final String regex = "[1-9]|[1-9][0-9]|[1-9][0-9][0-9]|[1-9][0-9][0-9][0-9]";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    //Method to check if the String is an ISO country name
    public static boolean validateCountryName(String countryName) {
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
    public static List[] getCountryList() {
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

}

