<#assign ticket_header>
    发票头部信息,包括:
    公司名称:xx有限公司
    地址:xxx市xxx区xx路xx处
    电话:xxxxxx
    发票代码:xxxxx
</#assign>

${ticket_header}

<table>
    <tr>
        <th>品项</th>
        <th>数量</th>
        <th>单价</th>
        <th>金额</th>
    </tr>

    <#list invoiceItems as item>
        <tr>
            <td>${item.name}</td>
            <td>${item.count}</td>
            <td>${item.unitPrice}</td>
            <td>${item.amount}</td>
        </tr>
    </#list>
</table>

<#assign ticket_footer>
    已收金额:xxxxx元
    SalesPerson:xxx
    Date:xx年xx月xx日
</#assign>

${ticket_footer}