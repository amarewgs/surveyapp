<script type="text/javascript">
	var deletelink;
        $(function(){

            // Dialog
            $('#dialog').dialog({
                resizable: false,
                autoOpen: false,
                width: 300,
                height: 140,
                modal: true,
                buttons: {
                    "Ok": function() {
                        $(this).dialog("close");
                        window.location = deletelink;
                    },
                    "Cancel": function() {
                        $(this).dialog("close");
                    }
                }
            });

            // Dialog Link
            /* $('#delete_link').click(function(){
            	deletelink = $(this).attr('data-href');
            	console.log(deletelink);
                $('#dialog').dialog('open');
                return false;
            }); */

        });
        
        function openDialog(obj) {        	
        	deletelink = $(obj).attr('data-href');
        	console.log(deletelink);
            $('#dialog').dialog('open');
            return false;
            
        }
    </script>