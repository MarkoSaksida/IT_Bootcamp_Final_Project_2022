package automationpractice.com.helpers;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "invalidEmailDataForCreateAccountEmailField")
    public Object[][] invalidEmailDataForCreateAccountEmailField() {
        return new Object[][]{
                {" "},
                {"@mail.de"},
                {"mail@mail"},
        };
    }

    @DataProvider(name = "validDataForAccountCreation")
    public Object[][] validDataForAccountCreation() {
        return new Object[][]{
                {"mr"},
                {"ms"},
        };
    }

    @DataProvider(name = "incompleteCredentialsForSignIn")
    public Object[][] incompleteCredentialsForSignIn() {
        return new Object[][] {
                { "", ""},
                { "", "123456"},
        };
    }

    @DataProvider(name = "paymentMethod")
    public Object[][] paymentMethod() {
        return new Object[][] {
                { "bank wire"},
                { "check"},
        };
    }

    @DataProvider(name = "socialMediaLinks")
    public Object[][] socialMediaLinks() {
        return new Object[][] {
                {"facebook", "https://www.facebook.com/groups/525066904174158/"},
                {"twitter", "https://twitter.com/seleniumfrmwrk"},
                {"youtube", "https://www.youtube.com/channel/UCHl59sI3SRjQ-qPcTrgt0tA"},
        };
    }

    @DataProvider(name = "dataForContactUsForm")
    public Object[][] dataForContactUsForm() {
        return new Object[][] {
                {"Customer service", "My dress arrived all torn, with blue stains."},
                {"Webmaster", "Your dress section is down, and I need one ASAP."},
        };
    }
}
