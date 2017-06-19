<#macro select selectState >
<#local stateText = '审核通过'>
<!--选择框-->
<#if (selectState == '00')><#local stateText = '审核中'></#if>
<#if (selectState == '01')><#local stateText = '审核通过'></#if>
<#if (selectState == '03')><#local stateText = '同步中'></#if>
${stateText}			

</#macro>