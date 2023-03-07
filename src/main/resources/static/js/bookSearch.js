let subArray =[]
let img
let modalImg
let captionText
let modal
let span
function querySearch(query) {


    fetch(`https://openlibrary.org/search.json?q=${query}`).then(res => res.json()).then(async res => {
        console.log(res)
        for (let i = 0; i < 7; i++) {
            if (res.docs[i].isbn === undefined) {

            } else {
                let html = "";
                html += `<div class="col-4 bg-light" id="pic${i}"">`

                html +=  `<div class="col-6 col-md-3"><img id="myImg${i}" title="${res.docs[i].title}" src="https://covers.openlibrary.org/b/isbn/${res.docs[i].isbn[0]}-M.jpg" alt="Title: ${res.docs[i].title} <br> Author: ${res.docs[i].author_name[0]}">
                          <div id="myModal${i}" class="modal" title="${res.docs[i].author_name[0]}">
                          <span class="close" id="span${i}">&times;</span>
                          <img class="modal-content" id="img${i}" alt="">
                          <div id="caption${i}"></div>
                          </div>`
                html += `<h6>${res.docs[i].title}</h6>`
                html += `<input type="submit" value="Add" class="add mb-4" name="add"></div></div>`


                document.getElementById("books").innerHTML += html


            }
            modal = document.getElementById(`myModal${i}`);

            // Get the image and insert it inside the modal - use its "alt" text as a caption get image its correct id
            img = document.getElementById(`myImg${i}`);
            modalImg = document.getElementById(`img${i}`);
            captionText = document.getElementById(`caption${i}`);
            $(captionText).css({
                'margin': 'auto',
                'display': 'block',
                'width': '80%',
                'max-width': '700px',
                'text-align': 'center',
                'color': '#ccc',
                'padding': '10px 0',
                'height': '150px',
            });
            $(img).css({
                'border-radius': '5px',
                'cursor': 'pointer',
                'transition': '0.3s',
                'aspect-ratio': '153/217',
                'max-width': '100%',
                'max-height': '100%'
            });



            let addBtns = document.querySelectorAll(".add");
            addBtns.forEach(x => {
                x.addEventListener("click", function (e) {
                    e.preventDefault()

                    document.getElementById("isbn").value = x.parentElement.firstElementChild.src
                    document.getElementById("author").value = x.parentElement.firstElementChild.nextElementSibling.title
                    document.getElementById("title").value = x.parentElement.firstElementChild.title
                    document.querySelector("form").submit()
                })
            })


        }
        $('img').click(function(e){

            e.currentTarget.nextElementSibling.style.display = "block"

            e.currentTarget.nextElementSibling.firstElementChild.nextElementSibling.src = this.src;

            e.currentTarget.nextElementSibling.firstElementChild.nextElementSibling.nextElementSibling.innerHTML = this.alt

        })


        // Get the <span> element that closes the modal  with an icremented id not class
        // span = document.getElementById(`span${i}`);

        // When the user clicks on <span> (x), close the modal
        $('span').click(function(e) {

            e.currentTarget.parentElement.style.display = "none"

        })


        let imgs = document.querySelectorAll("img");

        for (let i = 0; i < imgs.length; i++) {
            const fetcher = await fetch(imgs[i].src).then(res => res.blob())

            if (fetcher.size < 1000) {
                imgs[i].parentElement.parentElement.remove()
            }
        }


    })
}

document.getElementById("submit").addEventListener("click", function (e) {

    // e.preventDefault();
    let query = document.getElementById("query").value;
    querySearch(query)


})




