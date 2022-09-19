package hydro.pi.bridge.system.service;

import org.apache.commons.text.RandomStringGenerator;

import com.hydro.common.dictionary.data.HydroSystem;

import hydro.pi.bridge.api.ApiClient;

/**
 * Deals with the management of a hydroponics system.
 * 
 * @author Sam Butler
 * @since September 17, 2022
 */
public class SystemManagerService {
    private static final String SYSTEM_REGISTER_URL = "/api/system-app/system/register";
    private static final String SYSTEM_NAME_PREFIX = "HYDRO_SYSTEM";
    private static final String ACCEPTED_PASSWORD_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final String ACCEPTED_USERNAME_CHARS = "0123456789";
    private static final int PASSWORD_LENGTH = 64;
    private static final int USERNAME_LENGTH = 16;

    private final ApiClient apiClient;

    public SystemManagerService() {
        this.apiClient = new ApiClient();
    }

    /**
     * Registers the new hydroponics system. If the system has already been
     * registered than this method will do nothing and return.
     */
    public void registerSystem() {
        boolean systemExists = false;

        if(!systemExists) {
            this.apiClient.post(SYSTEM_REGISTER_URL, buildSystem(), HydroSystem.class);
        }
    }

    /**
     * Builds out a new {@link HydroSystem} with the username and password set to be
     * created.
     * 
     * @return The new hydro system to be created.
     */
    private HydroSystem buildSystem() {
        HydroSystem newSystem = new HydroSystem();
        newSystem.setName(generateRandomSystemName());
        newSystem.setPassword(generateRandomSystemPassword());
        return newSystem;
    }

    /**
     * Generates a random System password for when a system is first being
     * registered.
     * 
     * @return The string of the generated password.
     */
    private String generateRandomSystemPassword() {
        RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder()
                .selectFrom(ACCEPTED_PASSWORD_CHARS.toCharArray()).build();
        return pwdGenerator.generate(PASSWORD_LENGTH);
    }

    /**
     * Generates a random username for the system. The user can change it later when
     * they associate to the system.
     * 
     * @return The random system username.
     */
    private String generateRandomSystemName() {
        RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder()
                .selectFrom(ACCEPTED_USERNAME_CHARS.toCharArray()).build();
        return String.format("%s__%s", SYSTEM_NAME_PREFIX, pwdGenerator.generate(USERNAME_LENGTH));
    }
}
