package com.junit.bankapp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CheckStatus.class, CheckValidUsers.class, UserRegister.class })
public class AllTests {

}
