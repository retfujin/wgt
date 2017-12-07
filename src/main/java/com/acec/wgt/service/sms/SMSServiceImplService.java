
package com.acec.wgt.service.sms;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "SMSServiceImplService", targetNamespace = "http://HLimpl.sms.bnet.com/", wsdlLocation = "http://60.166.26.45:8889/SMSC/service/smsService?wsdl")
public class SMSServiceImplService
    extends Service
{

    private final static URL SMSSERVICEIMPLSERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(SMSServiceImplService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = SMSServiceImplService.class.getResource(".");
            url = new URL(baseUrl, "http://60.166.26.45:8889/SMSC/service/smsService?wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'http://60.166.26.45:8889/SMSC/service/smsService?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        SMSSERVICEIMPLSERVICE_WSDL_LOCATION = url;
    }

    public SMSServiceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SMSServiceImplService() {
        super(SMSSERVICEIMPLSERVICE_WSDL_LOCATION, new QName("http://HLimpl.sms.bnet.com/", "SMSServiceImplService"));
    }

    /**
     * 
     * @return
     *     returns SMSServiceImpl
     */
    @WebEndpoint(name = "SMSServiceImplPort")
    public SMSServiceImpl getSMSServiceImplPort() {
        return super.getPort(new QName("http://HLimpl.sms.bnet.com/", "SMSServiceImplPort"), SMSServiceImpl.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SMSServiceImpl
     */
    @WebEndpoint(name = "SMSServiceImplPort")
    public SMSServiceImpl getSMSServiceImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://HLimpl.sms.bnet.com/", "SMSServiceImplPort"), SMSServiceImpl.class, features);
    }

}
