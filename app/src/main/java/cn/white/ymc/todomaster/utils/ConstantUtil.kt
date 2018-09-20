package cn.white.ymc.todomaster.utils

import android.widget.Toast

/**
 *  String 常量类
 *
 * @packageName: cn.white.ymc.todomaster.utils
 * @fileName: ConstantUtil
 * @date: 2018/9/18  13:33
 * @author: ymc
 * @QQ:745612618
 *
 * const 类似于 java 的  static final。可以不产生 get 方法。有效规避65535方法数问题
 */

object ConstantUtil{

    var toast : Toast ?= null

    /**
     * 访问网络 基础 url
     */
    const val BASE_URL : String = "http://www.wanandroid.com/"

    /**
     * ssl key
     */
    const val SSL_KEY : String  = "-----BEGIN CERTIFICATE-----\n" + "MIIFjzCCBHegAwIBAgIQNILLl/rXAokv4nR6PLLyzjANBgkqhkiG9w0BAQsFADCB\n" + "lDELMAkGA1UEBhMCVVMxHTAbBgNVBAoTFFN5bWFudGVjIENvcnBvcmF0aW9uMR8w\n" + "HQYDVQQLExZTeW1hbnRlYyBUcnVzdCBOZXR3b3JrMR0wGwYDVQQLExREb21haW4g\n" + "VmFsaWRhdGVkIFNTTDEmMCQGA1UEAxMdU3ltYW50ZWMgQmFzaWMgRFYgU1NMIENB\n" + "IC0gRzIwHhcNMTcwOTEzMDAwMDAwWhcNMTgwOTEzMjM1OTU5WjAaMRgwFgYDVQQD\n" + "DA9jYW5nc2hlbmc3Ny5jb20wggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIB\n" + "AQCEHBJzUVQb3UYhmnFZGtPLhfI5y4qa6Fa2MdHItCYTefagp5PDC1H1A3j4sCl4\n" + "8/GqaIp0TTVRo1z7gXXtYPzXFJl1225Gt9Pcf2F3AMiHiDR3rfKztfCmvxQMW7jx\n" + "owLvrzZzQ+haSB2EgGCYjKgrWJv/5MoqIGZ5GwtTMFKe5WHjErKtL+Vua4pKr1xn\n" + "cxzKVMYiSz3IfJu/z3170JIXH+JjS80r7v2fCgb7/L5/nrXOPVt64IlQUBfSZGvm\n" + "AnNiJVrOlOkcgWZ7HCNtIqtxTO36HovD1FFPBSVO3LVO5+wH7ch6TmpO6Cn9/w2p\n" + "qmaIjhiPGMQU1Y9UdwepkIMHAgMBAAGjggJUMIICUDAvBgNVHREEKDAmgg9jYW5n\n" + "c2hlbmc3Ny5jb22CE3d3dy5jYW5nc2hlbmc3Ny5jb20wCQYDVR0TBAIwADBhBgNV\n" + "HSAEWjBYMFYGBmeBDAECATBMMCMGCCsGAQUFBwIBFhdodHRwczovL2Quc3ltY2Iu\n" + "Y29tL2NwczAlBggrBgEFBQcCAjAZDBdodHRwczovL2Quc3ltY2IuY29tL3JwYTAf\n" + "BgNVHSMEGDAWgBTKrF3hkC/x74zUnzUB4QE7oM7BdzAOBgNVHQ8BAf8EBAMCBaAw\n" + "HQYDVR0lBBYwFAYIKwYBBQUHAwEGCCsGAQUFBwMCMFcGCCsGAQUFBwEBBEswSTAf\n" + "BggrBgEFBQcwAYYTaHR0cDovL2hkLnN5bWNkLmNvbTAmBggrBgEFBQcwAoYaaHR0\n" + "cDovL2hkLnN5bWNiLmNvbS9oZC5jcnQwggEEBgorBgEEAdZ5AgQCBIH1BIHyAPAA\n" + "dQDd6x0reg1PpiCLga2BaHB+Lo6dAdVciI09EcTNtuy+zAAAAV57VCbzAAAEAwBG\n" + "MEQCICUq+Io57se7PnccYREOe19i4WKMk1WUqCelHo+anA0XAiBg18Lc1N0R/wl2\n" + "787hUZe9eGM1ScDIxf9vO1Yt/eYMpAB3AKS5CZC0GFgUh7sTosxncAo8NZgE+Rvf\n" + "uON3zQ7IDdwQAAABXntUJyoAAAQDAEgwRgIhAJk9I0AUpwUsYqtLJdXZw6iXCN7x\n" + "w+bX3o0wgt/IJkWXAiEA3YYUZtwye7Hv0zFRnjbZcabqcxBkbVEG584Y4htwFY8w\n" + "DQYJKoZIhvcNAQELBQADggEBAHw5nxEXXnKLuzkNyQjSo2To6uhfxl/JKPsEGeDD\n" + "MyVMAu0MvdBi9+cvm8pRA0rU1Q0iBKE9aUhBP9haUjKeq0oJEY9VX9/Jdze2qTe2\n" + "2plQCwVMVii5ehwxOvG3x/0R+I/Rq/YCasdZbA0n1rGYh7KNoNjWH9y1c0nyK2Kk\n" + "fvA2g7llDoXJXI4CepjUGfLlm4OwLVPm9dhdNj9ZhfqYvgOU/PPm85YvCdPjqmkL\n" + "hho2yjXMnFWNnfPaVhe9hSDoesNG1OhmEj+7yjSoQRw99gUYyD1+pJk8vhBk/HVM\n" + "fFj7cE7nnvBXd9n2uRaZ3/Bvpo5YTcp8lQ/sJzqk0s/R9i8=\n" + "-----END CERTIFICATE-----"
    /**
     * MIDDLE_KEY
     */
    const val MIDDLE_KEY : String = "-----BEGIN CERTIFICATE-----\n" + "MIIFXDCCBESgAwIBAgIQGHf2ZWingrup+0La5E4z1zANBgkqhkiG9w0BAQsFADCB\n" + "vTELMAkGA1UEBhMCVVMxFzAVBgNVBAoTDlZlcmlTaWduLCBJbmMuMR8wHQYDVQQL\n" + "ExZWZXJpU2lnbiBUcnVzdCBOZXR3b3JrMTowOAYDVQQLEzEoYykgMjAwOCBWZXJp\n" + "U2lnbiwgSW5jLiAtIEZvciBhdXRob3JpemVkIHVzZSBvbmx5MTgwNgYDVQQDEy9W\n" + "ZXJpU2lnbiBVbml2ZXJzYWwgUm9vdCBDZXJ0aWZpY2F0aW9uIEF1dGhvcml0eTAe\n" + "Fw0xNjA2MDcwMDAwMDBaFw0yNjA2MDYyMzU5NTlaMIGUMQswCQYDVQQGEwJVUzEd\n" + "MBsGA1UEChMUU3ltYW50ZWMgQ29ycG9yYXRpb24xHzAdBgNVBAsTFlN5bWFudGVj\n" + "IFRydXN0IE5ldHdvcmsxHTAbBgNVBAsTFERvbWFpbiBWYWxpZGF0ZWQgU1NMMSYw\n" + "JAYDVQQDEx1TeW1hbnRlYyBCYXNpYyBEViBTU0wgQ0EgLSBHMjCCASIwDQYJKoZI\n" + "hvcNAQEBBQADggEPADCCAQoCggEBALBoKGatE+oJe9ZT5t7YXUWn15vacpx+XFcf\n" + "xIhQRX75qaio8fgcsmQkY2zNyWRickmM5laehuW7rE2uZzeaKEeYEiCsNGhtTk8X\n" + "d3subeM1AINYEregPSuFlDpn+h7Cyw8au/pH6z/lGRRAA7LCsgDjMMreDrq0WKqt\n" + "xtoZ6Q9DExdztytlCMFhEWOS/AixMmI/4Gu7S/wGD0dg86i8J0DRjdPq+NDbzk/+\n" + "vQsBEh0hGbsTKYi0owy23REgytHWMsZi+s6JXAW3eI0fgZ2bynaALE4GVu0tj8hk\n" + "wEqD0KWQ1Ay4M2z/3Jv17lZumTtv+qEH2aq8ayPIpqJ0qcdctrECAwEAAaOCAX0w\n" + "ggF5MBIGA1UdEwEB/wQIMAYBAf8CAQAwNgYDVR0fBC8wLTAroCmgJ4YlaHR0cDov\n" + "L3Muc3ltY2IuY29tL3VuaXZlcnNhbC1yb290LmNybDAdBgNVHSUEFjAUBggrBgEF\n" + "BQcDAQYIKwYBBQUHAwIwDgYDVR0PAQH/BAQDAgEGMC4GCCsGAQUFBwEBBCIwIDAe\n" + "BggrBgEFBQcwAYYSaHR0cDovL3Muc3ltY2QuY29tMGEGA1UdIARaMFgwVgYGZ4EM\n" + "AQIBMEwwIwYIKwYBBQUHAgEWF2h0dHBzOi8vZC5zeW1jYi5jb20vY3BzMCUGCCsG\n" + "AQUFBwICMBkaF2h0dHBzOi8vZC5zeW1jYi5jb20vcnBhMCkGA1UdEQQiMCCkHjAc\n" + "MRowGAYDVQQDExFTeW1hbnRlY1BLSS0yLTU1NjAdBgNVHQ4EFgQUyqxd4ZAv8e+M\n" + "1J81AeEBO6DOwXcwHwYDVR0jBBgwFoAUtnf6aUhHn1MS1cLqBzJ2B9GXBxkwDQYJ\n" + "KoZIhvcNAQELBQADggEBAJRdaWPJpyEdGNMxyRkR93sap0nmeN3WoBUOJlb6lCbu\n" + "EX/5zhB1Yu9RNqdIFlVYUX/gEURpNDqKQb90xJq3jdfg3z/bQ90beLuohBLREcCm\n" + "nz78sfnyp7Z6gfXxyOYfy/MTA7866NFN5GEcQiGEBWdcitUhBPLuxzFUUGuGhiR7\n" + "f0cUVPq6ADcCDpqOwnFbFFuJ7c+RGz/HG0pqxC0dPiHo1NIF7XK23l12nurVXOmc\n" + "FJ31PEqFT0Rcd2fs+vkuVfpN+EBI+SY/gHiCOx/BvsgWiE4VXHTYIL36PUKecCQ+\n" + "+y9RlQ60UOqmSTQEQkkAUAn7w/0LjXEnfwfMhPABUcU=\n" + "-----END CERTIFICATE-----"

    /**
     * int 网络返回值
     */
    const val REQUEST_ERROR : Int = -1
    const val REQUEST_SUCCESS : Int = 0
    /**
     * toast 时间
     */
    const val TOAST_SHORT : Int = 1000

    /**
     * 用户相关 sp key
     */
    const val SP_FILE_NAME = "todo_sp_data"

    const val USERNAME : String = "username"
    const val PASSWORD :String = "password"
    const val ISLOGIN :String = "is_login"

    const val TRUE : Boolean = true
    const val FALSE : Boolean = false


}