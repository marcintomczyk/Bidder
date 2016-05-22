<#import "layout/defaultLayout.ftl" as layout>
<@layout.bidderLayout>
  
  <head>
  <@sj.head />	
  <script language="JavaScript" src="/Bidder/js/jqxcore.js" type="text/javascript"></script>
  <script language="JavaScript" src="/Bidder/js/jqxdata.js" type="text/javascript"></script>
  <script language="JavaScript" src="/Bidder/js/jqxbuttons.js" type="text/javascript"></script>
  <script language="JavaScript" src="/Bidder/js/jqxscrollbar.js" type="text/javascript"></script>
  <script language="JavaScript" src="/Bidder/js/jqxcheckbox.js" type="text/javascript"></script>
  <script language="JavaScript" src="/Bidder/js/jqxlistbox.js" type="text/javascript"></script>
  <script language="JavaScript" src="/Bidder/js/jqxdropdownlist.js" type="text/javascript"></script>
  <script language="JavaScript" src="/Bidder/js/jqxgrid.js" type="text/javascript"></script>
  <script language="JavaScript" src="/Bidder/js/jqxgrid.sort.js" type="text/javascript"></script>
  <script language="JavaScript" src="/Bidder/js/jqxgrid.pager.js" type="text/javascript"></script>
  <script language="JavaScript" src="/Bidder/js/jqxgrid.selection.js" type="text/javascript"></script>
  <script language="JavaScript" src="/Bidder/js/jqxgrid.edit.js" type="text/javascript"></script>
  <script language="JavaScript" src="/Bidder/js/jqxmenu.js" type="text/javascript"></script>
  <script language="JavaScript" src="/Bidder/js/gettheme.js" type="text/javascript"></script> 

  <script type="text/javascript">

	$(document).ready(function () {
	
	//var allBids = '<@s.property value="allBids" escapeJavaScript="true"/>';
	//var firstBid = '<@s.property value="allBids[0]"/>';
	
	
	//alert("ready !!!");
	//alert("firstBid: " + firstBid);
	
	//var tempBidId = '<@s.property value="allBids[0].id"/>';
	//var tempBidCost = '<@s.property value="allBids[0].cost"/>';
	//alert(tempBidId + ", " + tempBidCost);

			// prepare the data
            var convertedData = new Array();
            //var data = '<@s.property value="allBids"/>';
            
            //alert("data: " + data);
         
           //works ok
           //var tempBidId = '<@s.property value="allBids[0].id"/>';
           
           //alert("iterating through allBids");
           
           var counter = 0;
           <@s.iterator var="bid" status="status" value="allBids">
           		var row = {};
           		var tmpBidId = '<@s.property value="#bid.id" />'
           		var tmpBidCost = '<@s.property value="#bid.cost" />'
           		
           		var tmpAllSites = "";
           		//iterating through the sites
           		<@s.iterator var="site" status="status" value="#bid.sites">
           			var url = '<@s.property value="#site.url" />'
           			//alert("url: " + url);
           			tmpAllSites = tmpAllSites + " " + url;
           		</@s.iterator>
           		
           		row["id"] = tmpBidId;
                row["cost"] = tmpBidCost;
                row["sites"] = tmpAllSites;
                convertedData[counter] = row;
                counter += 1;
   			</@s.iterator>
          
            var source =
            {
                localdata: convertedData,
                datatype: "array"
            };
            var dataAdapter = new $.jqx.dataAdapter(source, {
                downloadComplete: function (data, status, xhr) { },
                loadComplete: function (data) { },
                loadError: function (xhr, status, error) { }
            });
            
            var pagerrenderer = function () {
                var element = $("<div style='margin-top: 5px; width: 100%; height: 100%;'></div>");
                var paginginfo = $("#jqxgrid").jqxGrid('getpaginginformation');
                for (i = 0; i < paginginfo.pagescount; i++) {
                    // add anchor tag with the page number for each page.
                    var anchor = $("<a style='padding: 5px;' href='#" + i + "'>" + i + "</a>");
                    anchor.appendTo(element);
                    anchor.click(function (event) {
                        // go to a page.
                        var pagenum = parseInt($(event.target).text());
                        $("#jqxgrid").jqxGrid('gotopage', pagenum);
                    });
                }
                return element;
            }
            
            $("#jqxgrid").jqxGrid(
            {
                width: 670,
                source: dataAdapter,
                pageable: true,
                pagerrenderer: pagerrenderer,
                columns: [
                  { text: 'Id', datafield: 'id', width: 100 },
                  { text: 'Cost', datafield: 'cost', width: 100 },
                  { text: 'Sites', datafield: 'sites', width: 500 }
                ]
            });
		});	
</script>
</head>
  
  <div id="content">
   <fieldset>
   		
   	 <div id='jqxWidget' style="font-size: 13px; font-family: Verdana; float: left;">
        <div id="jqxgrid">
        </div>
     </div>
   
   
   </fieldset>
    <@s.url id="createBidLink" action="createBid"/>
	<@s.a href="%{createBidLink}">Click Here to Add New Bid </@s.a>
	
  </div>
</@layout.bidderLayout>