/**
 * If the system select has changed then we want to update the attribute type options
 */
jQuery("select.system").live('change', function() {
	jQuery.ajax({
		type: "GET",
		url: "/metadata-service/rest/search/attributes",
		dataType: "json",
		data: {
			system: this.value
		},
		success: function(data) {
			jQuery("select.field").find('option').remove().end();
			jQuery.each(data, function(index, item) {
				var value = item['attrType'];
				var description = item['title'];
				jQuery("select.field").append(jQuery("<option value='" + value + "'>" + description + "</option>"));
			});
		},
		error: function(jqXHR, status) {
			alert('Error retrieving data: ' + status);
		}
	});
});

/**
 * Add a new search field and field type limitors
 */
jQuery("#addSearchField").live('click', function() {
	var newDiv = jQuery("#terms .term:last").clone(true);
	newDiv.find('input:text').val('');
	newDiv.find('select option[selected]').removeAttr('selected');
	newDiv.insertAfter("#terms .term:last");
});
