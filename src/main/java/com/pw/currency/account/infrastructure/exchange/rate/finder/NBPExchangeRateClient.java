package com.pw.currency.account.infrastructure.exchange.rate.finder;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(
        name = "${integration.rateFinder.nbp.name}",
        url = "${integration.rateFinder.nbp.url}",
        path = "${integration.rateFinder.nbp.path}"
)
interface NBPExchangeRateClient {

    @RequestMapping(method = RequestMethod.GET, value = "/rates/{table}/{code}/")
    NBPSingleExchangeRateResponse getRate(@PathVariable("table") String table,
                                          @PathVariable("code") String code);
}
