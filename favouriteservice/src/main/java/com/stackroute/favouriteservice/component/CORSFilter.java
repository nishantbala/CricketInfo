package com.stackroute.favouriteservice.component;

        import java.io.IOException;

        import javax.servlet.Filter;
        import javax.servlet.FilterChain;
        import javax.servlet.FilterConfig;
        import javax.servlet.ServletException;
        import javax.servlet.ServletRequest;
        import javax.servlet.ServletResponse;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;

public class CORSFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Credentials", "true");
        res.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        res.setHeader("Access-Control-Max-Age", "3600");
        res.setHeader("Access-Control-Allow-Headers",
                "X-Requested-With, Content-Type, Authorization, Origin, Accept, Access-Control-Request-Method, Access-Control-Request-Headers");
        HttpServletRequest req = (HttpServletRequest) request;
        String method = req.getMethod();
        if (method.equalsIgnoreCase("OPTIONS")) {
            ((HttpServletResponse) res).sendError(HttpServletResponse.SC_OK, "Success");
        } else {
            chain.doFilter(req, response);
        }

    }

    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }

}
