$(document).ready(function () {
    function readURL(input) {
        if (input.files && input.files[0]) {
            let reader = new FileReader();

            reader.onload = (e) => {
                $('#blah').attr({'src': e.target.result}).css('display', 'block')
            };
            reader.readAsDataURL(input.files[0]);
        }
    }

    $("#file").change(function () {
        readURL(this);
    });

    $('#btn-filter').on('click', () => {

    });

    $('.delete').on('click', (e) => {
        let title = $(e.target).data('title');
        console.log(title)
        Swal.fire({
            title: 'Are you sure to delete?',
            text: "This cannot be undone!",
            type: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, delete it!'
        }).then((result) => {
            if (result.value) {
                $.notify(title, "success");
                setInterval(() => {
                    $(e.target).closest('form').submit();
                }, 1200);
            }
        })
    });
    let toggle = true;
    $('#btn-change-navbar').on('click', () => {
        if (toggle === false) {
            toggle = true;
            $('#navbar-replace').load('navbar-dropdown');
        } else {
            toggle = false;
            $('#navbar-replace').load('navbar');
        }
    });
});