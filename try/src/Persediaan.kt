class Persediaan : Gudang {
    companion object {
        internal val listObat = mutableListOf<Obat>()

        fun addObat (obat: Obat){
            listObat.add(obat)
            println("\nInsert Data Obat Berhasil")
        }
        fun readObat(){
            for (obat in listObat){
                println(obat)
            }
        }
        fun updateObat (nmObat: String){
            var ada = false
            for (obat in listObat){
                if (obat.nmObat == nmObat){
                    ada = true
                    println("\n-----Update Data Obat-----")
                    print("Nama Obat : ")
                    val nmObat1 = readLine()
                    print("Nama Pabrik : ")
                    val nmPabrik1 = readLine()
                    print("Dosis Obat :  ")
                    val dosis1 = readLine()

                    obat.nmObat = nmObat1 ?: ""
                    obat.nmPabrik = nmPabrik1 ?: ""
                    obat.dosis = dosis1 ?: ""
                }
            }
            if (ada){
                println("\nUpdate Data Obat Berhasil")
            } else {
                println("\nNama Obat Tidak Ditemukan")
            }
        }
        fun deleteObat (nmObat: String){
            var ada = false
            var obat1: Obat? = null
            for (obat in listObat){
                if (obat.nmObat == nmObat){
                    ada = true
                    obat1 = Obat(obat.nmObat, obat.nmPabrik, obat.dosis, obat.stok)
                }
            }
            if (ada){
                listObat.remove(obat1)
                println("\nDelete Data Obat Berhasil")
            } else {
                println("\nNama Obat Tidak Ditemukan")
            }
        }
    }

    override fun stokIn(nmObat: String, stokMsk: Int) {
        var namaObatIn: Obat?
        for (obat in listObat){
            if (obat.nmObat == nmObat){
                namaObatIn = obat
                namaObatIn.stok = namaObatIn.stok.plus(stokMsk)
            }
        }
    }

    override fun stokOut(nmObat: String, stokKlr: Int) {
        var namaObatOut: Obat?
        for (obat in listObat){
            if (obat.nmObat == nmObat){
                namaObatOut = obat
                if (stokKlr <= obat.stok){
                    namaObatOut.stok = namaObatOut.stok.minus(stokKlr)
                } else {
                    namaObatOut.stok = namaObatOut.stok
                }
            }
        }
    }
}

