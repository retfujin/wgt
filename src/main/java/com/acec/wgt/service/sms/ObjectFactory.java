
package com.acec.wgt.service.sms;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.thszxy.service.sms package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _RecvSMSResponse_QNAME = new QName("http://HLimpl.sms.bnet.com/", "recvSMSResponse");
    private final static QName _SendSMS_QNAME = new QName("http://HLimpl.sms.bnet.com/", "sendSMS");
    private final static QName _RecvMTStateResponse_QNAME = new QName("http://HLimpl.sms.bnet.com/", "recvMTStateResponse");
    private final static QName _RecvSMS_QNAME = new QName("http://HLimpl.sms.bnet.com/", "recvSMS");
    private final static QName _SendSMSResponse_QNAME = new QName("http://HLimpl.sms.bnet.com/", "sendSMSResponse");
    private final static QName _RecvMTState_QNAME = new QName("http://HLimpl.sms.bnet.com/", "recvMTState");
    private final static QName _GetRestNum_QNAME = new QName("http://HLimpl.sms.bnet.com/", "getRestNum");
    private final static QName _GetRestNumResponse_QNAME = new QName("http://HLimpl.sms.bnet.com/", "getRestNumResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.thszxy.service.sms
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RecvMTStateResponse }
     * 
     */
    public RecvMTStateResponse createRecvMTStateResponse() {
        return new RecvMTStateResponse();
    }

    /**
     * Create an instance of {@link SendSMS }
     * 
     */
    public SendSMS createSendSMS() {
        return new SendSMS();
    }

    /**
     * Create an instance of {@link SendSMSResponse }
     * 
     */
    public SendSMSResponse createSendSMSResponse() {
        return new SendSMSResponse();
    }

    /**
     * Create an instance of {@link RecvSMS }
     * 
     */
    public RecvSMS createRecvSMS() {
        return new RecvSMS();
    }

    /**
     * Create an instance of {@link RecvMTState }
     * 
     */
    public RecvMTState createRecvMTState() {
        return new RecvMTState();
    }

    /**
     * Create an instance of {@link GetRestNum }
     * 
     */
    public GetRestNum createGetRestNum() {
        return new GetRestNum();
    }

    /**
     * Create an instance of {@link GetRestNumResponse }
     * 
     */
    public GetRestNumResponse createGetRestNumResponse() {
        return new GetRestNumResponse();
    }

    /**
     * Create an instance of {@link RecvSMSResponse }
     * 
     */
    public RecvSMSResponse createRecvSMSResponse() {
        return new RecvSMSResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RecvSMSResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://HLimpl.sms.bnet.com/", name = "recvSMSResponse")
    public JAXBElement<RecvSMSResponse> createRecvSMSResponse(RecvSMSResponse value) {
        return new JAXBElement<RecvSMSResponse>(_RecvSMSResponse_QNAME, RecvSMSResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendSMS }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://HLimpl.sms.bnet.com/", name = "sendSMS")
    public JAXBElement<SendSMS> createSendSMS(SendSMS value) {
        return new JAXBElement<SendSMS>(_SendSMS_QNAME, SendSMS.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RecvMTStateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://HLimpl.sms.bnet.com/", name = "recvMTStateResponse")
    public JAXBElement<RecvMTStateResponse> createRecvMTStateResponse(RecvMTStateResponse value) {
        return new JAXBElement<RecvMTStateResponse>(_RecvMTStateResponse_QNAME, RecvMTStateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RecvSMS }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://HLimpl.sms.bnet.com/", name = "recvSMS")
    public JAXBElement<RecvSMS> createRecvSMS(RecvSMS value) {
        return new JAXBElement<RecvSMS>(_RecvSMS_QNAME, RecvSMS.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendSMSResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://HLimpl.sms.bnet.com/", name = "sendSMSResponse")
    public JAXBElement<SendSMSResponse> createSendSMSResponse(SendSMSResponse value) {
        return new JAXBElement<SendSMSResponse>(_SendSMSResponse_QNAME, SendSMSResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RecvMTState }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://HLimpl.sms.bnet.com/", name = "recvMTState")
    public JAXBElement<RecvMTState> createRecvMTState(RecvMTState value) {
        return new JAXBElement<RecvMTState>(_RecvMTState_QNAME, RecvMTState.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRestNum }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://HLimpl.sms.bnet.com/", name = "getRestNum")
    public JAXBElement<GetRestNum> createGetRestNum(GetRestNum value) {
        return new JAXBElement<GetRestNum>(_GetRestNum_QNAME, GetRestNum.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRestNumResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://HLimpl.sms.bnet.com/", name = "getRestNumResponse")
    public JAXBElement<GetRestNumResponse> createGetRestNumResponse(GetRestNumResponse value) {
        return new JAXBElement<GetRestNumResponse>(_GetRestNumResponse_QNAME, GetRestNumResponse.class, null, value);
    }

}
