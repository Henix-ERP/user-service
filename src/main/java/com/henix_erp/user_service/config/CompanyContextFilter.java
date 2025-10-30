package com.henix_erp.user_service.config;

import com.henix_erp.common.tenant.CompanyContext;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CompanyContextFilter extends HttpFilter {

    @Override
    public void doFilter(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        try {
            String companyIdHeader = request.getHeader("X-Company-Id");
            if(companyIdHeader != null) {
                CompanyContext.setCompanyId(Long.parseLong(companyIdHeader));
            }
            System.out.println("Setting CompanyContext: " + companyIdHeader);
            chain.doFilter(request, response);
        } finally {
            CompanyContext.clear();
        }
    }
}
