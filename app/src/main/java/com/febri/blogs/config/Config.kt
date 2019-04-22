package com.febri.blogs.config

object Config {

//    private const val Host="http://192.168.56.1/Webservice/"
    private const val Host="http://mup-admin.server411.tech/"
    val url_login =  Host+"index.php/Webservice/login_user"
    val url_registrasi =  Host+"index.php/Webservice/register_user"

    val url_kategori = Host+"index.php/Webservice/kategori"
    val url_sub_kategori = Host+"index.php/Webservice/subkategori"
    val url_sub_kategori_get = Host+"index.php/Webservice/subkategori_get/"
    val url_content = Host+"index.php/Webservice/contentlist/"
    val url_detail_content = Host+"index.php/Webservice/contentdetails/"

    val url_gambar =  Host+"assets/picture/"


    const val id="id"
}
