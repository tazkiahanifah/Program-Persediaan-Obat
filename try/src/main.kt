import Persediaan.Companion.listObat
import kotlin.system.exitProcess

fun validateStok(stokKlr: Int, obat:Obat){
    if (stokKlr > obat.stok){
        throw ValidationException("Stok Obat Yang Tersedia Hanya ${obat.stok}")
    }
}

fun main(){
    val persediaan = Persediaan()
    dummyObat()
    while (true){
        println("""
        |
        |Program Persediaan Obat
        |1. Insert Data Obat
        |2. Stok Obat Masuk
        |3. Stok Obat Keluar
        |4. View Data Obat
        |5. Update Data Obat
        |6. Delete Data Obat
        |7. Exit
    """.trimIndent())
        print("Pilihan Menu : ")
        val menu = readLine()
        when (menu.toString().toLowerCase().trim()){
            "1" -> {
                println("\n-----Insert Data Obat-----")
                print("Nama Obat : ")
                val nmObat = readLine()
                print("Nama Pabrik : ")
                val nmPabrik = readLine()
                print("Dosis Obat : ")
                val dosis = readLine()
                print("Stok Obat : ")
                val stok = readLine()?.toInt()

                val obat = stok?.let{
                    Obat (
                            nmObat.toString().toLowerCase(), nmPabrik.toString().toLowerCase(), dosis.toString().toLowerCase(), it
                    )
                }
                obat?.let {Persediaan.addObat(it)}
            }
            "2" -> {
                println("\n-----Stok Obat Masuk-----")
                print("Nama Obat : ")
                val nmObat = readLine()

                var ada = false
                for (obat in listObat){
                    if (obat.nmObat == nmObat){
                        ada = true
                        print("Jumlah Obat Masuk : ")
                        val stokMsk: Int? = readLine()?.toInt()
                        persediaan.stokIn(nmObat.toString(), stokMsk!!)
                    }
                }
                if (ada){
                    println("\nPenambahan Jumlah Stok Berhasil")
                } else {
                    println("\nNama Obat Tidak Ditemukan")
                }
            }
            "3" -> {
                println("\n-----Stok Obat Keluar-----")
                print("Nama Obat : ")
                val nmObat = readLine()

                var ada = false
                var valid = false
                for (obat in listObat){
                    if (obat.nmObat == nmObat){
                        print("Jumlah Obat Keluar : ")
                        val stokKlr: Int? = readLine()?.toInt()
                        try{
                            validateStok(stokKlr!!, obat)
                            ada = true
                        } catch (error: ValidationException){
                            println("${error.message}")
                            valid = true
                        }
                        persediaan.stokOut(nmObat.toString(), stokKlr!!.toInt())
                    }
                }
                when {
                    ada -> {
                        println("\nPengurangan Jumlah Stok Berhasil")
                    }
                    valid -> {
                        println("\nPengurangan Jumlah Stok Gagal")
                    }
                    else -> {
                        println("\nNama Obat Tidak Ditemukan")
                    }
                }
            }
            "4" -> {
                println("\n-----Daftar Obat-----")
                Persediaan.readObat()
            }
            "5" -> {
                println("\n-----Update Data Obat-----")
                print("Nama Obat : ")
                val nmObat = readLine()
                Persediaan.updateObat(nmObat ?: "")
            }
            "6" -> {
                println("\n-----Delete Data Obat-----")
                print("Nama Obat : ")
                val nmObat = readLine()
                Persediaan.deleteObat(nmObat ?: "")
            }
            "7" -> {
                exitProcess(0)
            }
            else -> {
                println("\nPilihan Menu Tidak Tersedia")
            }
        }
    }
}
fun dummyObat(){
    val obat1 = Obat (
            "Meloxicam", "Kimia Farma", "15 Mg", 340
    )
    val obat2 = Obat (
            "Vectrine", "Sanbe Farma", "300 Mg", 250
    )
    val obat3 = Obat (
            "Vometa", "Dexa Medica", "10 Mg", 167
    )
    val obat4 = Obat (
            "Cataflam", "Novatris Indonesia", "50 Mg", 320
    )
    val obat5 = Obat (
            "Doxicor", "Genero Pharmaceutical", "100 Mg", 190
    )
    Persediaan.addObat(obat1)
    Persediaan.addObat(obat2)
    Persediaan.addObat(obat3)
    Persediaan.addObat(obat4)
    Persediaan.addObat(obat5)
}
