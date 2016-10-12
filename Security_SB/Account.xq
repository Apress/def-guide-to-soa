(:: pragma bea:global-element-parameter parameter="$getAccount1" element="ns0:getAccount" location="WSDL/SecureMessageService.wsdl" ::)
(:: pragma bea:global-element-return element="ns0:getAccountResponse" location="WSDL/SecureMessageService.wsdl" ::)

declare namespace xf = "http://tempuri.org/Security%20SB/Account/";
declare namespace ns0 = "http://www.alsb.com/SecureMessageService/";

declare function xf:Account($getAccount1 as element(ns0:getAccount))
    as element(ns0:getAccountResponse) {
        <ns0:getAccountResponse>
            <customerID>{ data($getAccount1/customerID) }</customerID>
            <customerName>Doe, John</customerName>
            <account>12345-6789-01234</account>
        </ns0:getAccountResponse>
};

declare variable $getAccount1 as element(ns0:getAccount) external;

xf:Account($getAccount1)
