xquery version "1.0";
declare namespace xf = "http://tempuri.org/GoodbyeWorld_SB/XQuery/Hello_to_Goodbye/";

declare function xf:Hello_to_Goodbye($helloStr as xs:string)
    as xs:string {
        replace($helloStr, 'Hello', 'Goodbye')
};

declare variable $helloStr as xs:string external;

xf:Hello_to_Goodbye($helloStr)
