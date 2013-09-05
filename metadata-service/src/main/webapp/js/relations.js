jQuery("label[for='relations']").live('click', function() {
	var relations = jQuery("#relations");
	if (relations.hasClass('search')) {
		var pathname = location.pathname.split("/");
		jQuery.ajax({
			type: "GET",
			url: "/metadata-service/rest/display/" + pathname[pathname.length - 1] + "/relation",
			dataType: "json",
			success: function(data) {
				if (data.length > 0) {
					jQuery("#relations").empty();
					var table = jQuery("<table></table>");
					
					var headerRow = jQuery("<tr></tr>");
					headerRow.append(jQuery("<th></th>").text("Source"));
					headerRow.append(jQuery("<th></th>").text("Title"));
					table.append(headerRow);
					
					jQuery.each(data, function(index, item) {
						var row = jQuery("<tr></tr>");
						row.append(jQuery("<td></td>").text(item.extSystem));
						var titleLink = jQuery("<a></a>", {
							text: item.title,
							href: "/metadata-service/rest/display/" + item.id
						});
						row.append(jQuery("<td></td>").append(titleLink));
						table.append(row);
					});
					
					jQuery("#relations").html(table);
				}
				else {
					jQuery("#relations").text("No Relations Found");
				}
				relations.removeClass('search');
			},
			error: function(jqXHR, status) {
				alert('Error retrieving data: ' + status);
			}
		});
	}
	if (relations.hasClass('hidden')) {
		relations.removeClass('hidden');
		jQuery(this).removeClass('hidden-text');
		jQuery(this).addClass('show-text');
	}
	else {
		relations.addClass('hidden');
		jQuery(this).removeClass('show-text');
		jQuery(this).addClass('hidden-text');
	}
});
