$(() =>
{
	$("div#calendar").append(new Calendar());
	
	$("div#pnlLeft")
		.resizable(
		{	handles:	'e'
		,	resize: 	function()
						{
							$("#right_pane").outerWidth($("#container").innerWidth() - $("#left_pane").outerWidth());
  						}
		});
		
	$("body, button, div, span, table")
		.disableSelection()
		.select(function(e)
		{
			e.preventDefault();
			e.stopImmediatePropagation();
		})
});