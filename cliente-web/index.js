const contenedorCodigo = document.getElementById("info-recurso");
const contenedorImagen = document.getElementById("info-imagen");
const imgServer = document.getElementById("image-fromserver");

async function obtenerInfoPelicula(){
    const extensionRecurso = document.getElementById("extensionRecurso");

    if( extensionRecurso.value != '' && extensionRecurso.value != undefined ){
        const url = "http://localhost:35000/?resource="+extensionRecurso.value;
        const respuesta = await fetch(url, {
            method: "GET"
        });
        const infoRecurso = await respuesta.json();

        let contenedorInfo = ""; 
        if( infoRecurso.resource === 'png' || infoRecurso.resource === 'jpg' ){
            contenedorInfo += `
                        <div class="card" style="width: 18rem;">
                            <div class="card-body">
                                <p class="card-text">1</p>
                                <p class="card-text">2</p>
                                <span class="badge bg-secondary">3</span></h6>
                            </div>
                        </div>`         
            imgServer.setAttribute('src', "data:image/jpg;base64," + infoRecurso.body);
            contenedorCodigo.innerHTML = '';
        } else if( infoRecurso.resource === 'html' ){
            contenedorCodigo.innerHTML = infoRecurso.body;
            imgServer.removeAttribute('src');
        } else if( infoRecurso.resource === 'css' || infoRecurso.resource === 'js' ){
            contenedorInfo += `
                        <div class="container" style="background: white; color: black;">
                            <div class="container">
                                <p class="card-text">${infoRecurso.body}</p>
                            </div>
                        </div>`
            contenedorCodigo.innerHTML = contenedorInfo;
            imgServer.removeAttribute('src');
        }
        
    } else {
        alert('Para buscar la informacíón de la pelicula, escriba en el input')
    }
}

const button = document.getElementById("btnBuscarRecurso");

button.addEventListener("click", obtenerInfoPelicula)