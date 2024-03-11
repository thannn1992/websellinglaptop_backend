package than.MK.weblaptop.backend.security;

public class Endpoints {
    public static final String FRONT_END_HOST = "http://localhost:3000";

    public static final String[] PUBLIC_GET_ENDPOINT = {
            "/",
            "/laptop",
            "/laptop/**",
            "/picture",
            "/picture/**",
            "/user/search/existsByUserName",
            "user/search/existsByEmail",
            "/api/account/active",

    };

    public static final String[] PUBLIC_POST_ENDPOINTS = {
            "/api/account/register",
            "/api/account/login"
    };
    public static final String[] ADMIN_GET_ENDPOINT = {
            "/user",
            "/user/**"
    };

    public static final String[] ADMIN_POST_ENDPOINT = {
            "/laptop/**",
            "/laptop"
    };
}
