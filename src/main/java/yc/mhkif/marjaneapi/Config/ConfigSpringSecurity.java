package yc.mhkif.marjaneapi.Config;




//@EnableWebSecurity
public class ConfigSpringSecurity {


    /*
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build());
        return manager;
    }






    //@Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /*
        http.securityMatcher("/marjane/api/v1/**")
                .authorizeHttpRequests((requests) -> {
            ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)requests.anyRequest()).authenticated();
        });
        http.httpBasic(Customizer.withDefaults());
        return http.build();


        http
                .csrf(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());
                //.formLogin(Customizer.withDefaults());
        return http.build();
    }

     */

}
