(:: pragma bea:mfl-element-parameter parameter="$customerEmailFormat" type="CustomerEmailFormat@" location="CustomerEmailFormat.mfl" ::)
(:: pragma bea:mfl-element-return type="CustomerFTPFormat@" location="CustomerFTPFormat.mfl" ::)

declare namespace xf = "http://tempuri.org/ServiceTypes_SB/CustomerMFLTransform/";

declare function xf:CustomerMFLTransform($customerEmailFormat as element())
    as element() {
        <CustomerFTPFormat>
        	<ID>{ $customerEmailFormat/CustomerID/text() }</ID>
            <Name>{ concat($customerEmailFormat/lastName , ', ', $customerEmailFormat/firstName) }</Name>
        </CustomerFTPFormat>
};

declare variable $customerEmailFormat as element() external;

xf:CustomerMFLTransform($customerEmailFormat)
