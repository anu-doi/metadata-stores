jQuery("label").live('click', function() {
	var forVal = jQuery(this).attr('for');
	jQuery("input[value='" + forVal + "']").click();
});

jQuery(".search input").live('change', function() {
	if (jQuery(this).attr('checked') == 'checked') {
		var value = jQuery(this).val();
		var clickedList = jQuery(this).closest("li");
		var numAdds = 0;
		jQuery.ajax({
			type: "GET",
			url: "/services/rest/admin/relation/" + value,
			cache: false,
			success: function(data) {
				clickedList.removeClass('search');
				var list = jQuery("<ol></ol>");
				jQuery.each(data, function(index, item) {
					var relatedIid = item['related-iid'];
					var relatedTitle = item['related-title'];
					if (relatedTitle == '' || relatedTitle === undefined) {
						relatedTitle = 'No Title Found';
					}
					var something = jQuery("input[value='" + relatedIid + "']").length;
					if (something == 0) {
						var listItem = jQuery("<li class='hasChildren search'></li>");
						listItem.append(jQuery("<label for='" + relatedIid + "' title='" + item['relation-value'] + "'></label>").text(relatedTitle));
						listItem.append(jQuery("<input type='checkbox' value='" + relatedIid + "' />"));
						list.append(listItem);
						numAdds = numAdds + 1;
					}
				});
				if (numAdds > 0) {
					clickedList.append(list);
				}
				else {
					clickedList.removeClass('hasChildren').addClass('noChildren');
				}
			},
			error: function(jqXHR, status) {
				console.log('Error');
			}
		});
	}
});