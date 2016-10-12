(:: pragma bea:global-element-parameter parameter="$getOrderInfoResponse1" element="ns1:getOrderInfoResponse" location="WSDL/SplitJoin_WS.wsdl" ::)
(:: pragma bea:global-element-return element="ns0:OrderInfo" location="WSDL/SplitJoinFlow.wsdl" ::)

declare namespace xf = "http://tempuri.org/SplitJoin_SB/orderXLA/";
declare namespace ns0 = "http://www.alsb.com/flow/";
declare namespace ns1 = "http://www.alsb.com/";

declare function xf:orderXLA($getOrderInfoResponse1 as element(ns1:getOrderInfoResponse))
    as element(ns0:OrderInfo) {
        <ns0:OrderInfo>
            {
                for $order in $getOrderInfoResponse1/order
                return
                    <order>
                        {
                            for $product in $order/product
                            return
                                <product>{ data($product) }</product>
                        }
                    </order>
            }
        </ns0:OrderInfo>
};

declare variable $getOrderInfoResponse1 as element(ns1:getOrderInfoResponse) external;

xf:orderXLA($getOrderInfoResponse1)
