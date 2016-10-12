(:: pragma bea:global-element-parameter parameter="$getBillingInfoResponse1" element="ns1:getBillingInfoResponse" location="WSDL/SplitJoin_WS.wsdl" ::)
(:: pragma bea:global-element-return element="ns0:BillingInfo" location="WSDL/SplitJoinFlow.wsdl" ::)

declare namespace xf = "http://tempuri.org/SplitJoin_SB/billingXLA/";
declare namespace ns0 = "http://www.alsb.com/flow/";
declare namespace ns1 = "http://www.alsb.com/";

declare function xf:billingXLA($getBillingInfoResponse1 as element(ns1:getBillingInfoResponse))
    as element(ns0:BillingInfo) {
        <ns0:BillingInfo>
            {
                for $bill in $getBillingInfoResponse1/bill
                return
                    <bill>
                        <billingDate>{ data($bill/date) }</billingDate>
                        <amount>{ data($bill/amount) }</amount>
                    </bill>
            }
        </ns0:BillingInfo>
};

declare variable $getBillingInfoResponse1 as element(ns1:getBillingInfoResponse) external;

xf:billingXLA($getBillingInfoResponse1)
