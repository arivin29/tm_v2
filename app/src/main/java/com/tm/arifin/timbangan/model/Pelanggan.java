package com.tm.arifin.timbangan.model;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Pelanggan {

    @SerializedName("id_pelanggan")
     
    private Integer idPelanggan;
    @SerializedName("lon")
     
    private String lon;
    @SerializedName("lat")
     
    private String lat;
    @SerializedName("id_provinsi")
     
    private Integer idProvinsi;
    @SerializedName("id_kabkot")
     
    private Integer idKabkot;
    @SerializedName("id_kecematan")
     
    private Integer idKecematan;
    @SerializedName("id_kelurahan")
     
    private Integer idKelurahan;
    @SerializedName("id_pdam")
     
    private Integer idPdam;
    @SerializedName("kd_pelanggan")
     
    private String kdPelanggan;
    @SerializedName("kd_konversi")
     
    private String kdKonversi;
    @SerializedName("kd_pelanggan_old")
     
    private String kdPelangganOld;
    @SerializedName("nama_pelanggan")
     
    private String namaPelanggan;
    @SerializedName("no_ktp")
     
    private String noKtp;
    @SerializedName("alamat")
     
    private String alamat;
    @SerializedName("tgl_lahir")

    private String tglLahir;
    @SerializedName("propinsi")
    
    private String propinsi;
    @SerializedName("kode_pos")
     
    private String kodePos;
    @SerializedName("jenis_kelamin")
     
    private String jenisKelamin;
    @SerializedName("email")
    
    private String email;
    @SerializedName("photo")
    
    private String photo;
    @SerializedName("tgl_daftar")
     
    private String tglDaftar;
    @SerializedName("tgl_penutupan")
     
    private String tglPenutupan;
    @SerializedName("status")
     
    private String status;
    @SerializedName("status_kawin")
     
    private String statusKawin;
    @SerializedName("agama")
     
    private String agama;
    @SerializedName("pekerjaan")
     
    private String pekerjaan;
    @SerializedName("id_prov")
     
    private Integer idProv;
    @SerializedName("telp")
     
    private String telp;
    @SerializedName("hp")
     
    private String hp;
    @SerializedName("kd_type")
     
    private String kdType;
    @SerializedName("kd_cabang")
     
    private String kdCabang;
    @SerializedName("kd_wilayah")
     
    private String kdWilayah;
    @SerializedName("kd_blok")
     
    private String kdBlok;
    @SerializedName("id_zona_baca")
     
    private Integer idZonaBaca;
    @SerializedName("id_zona_pipa")
     
    private Integer idZonaPipa;

    /**
     *
     * @return
     * The idPelanggan
     */
    public Integer getIdPelanggan() {
        return idPelanggan;
    }

    /**
     *
     * @param idPelanggan
     * The id_pelanggan
     */
    public void setIdPelanggan(Integer idPelanggan) {
        this.idPelanggan = idPelanggan;
    }

    /**
     *
     * @return
     * The lon
     */
    public String getLon() {
        return lon;
    }

    /**
     *
     * @param lon
     * The lon
     */
    public void setLon(String lon) {
        this.lon = lon;
    }

    /**
     *
     * @return
     * The lat
     */
    public String getLat() {
        return lat;
    }

    /**
     *
     * @param lat
     * The lat
     */
    public void setLat(String lat) {
        this.lat = lat;
    }

    /**
     *
     * @return
     * The idProvinsi
     */
    public Integer getIdProvinsi() {
        return idProvinsi;
    }

    /**
     *
     * @param idProvinsi
     * The id_provinsi
     */
    public void setIdProvinsi(Integer idProvinsi) {
        this.idProvinsi = idProvinsi;
    }

    /**
     *
     * @return
     * The idKabkot
     */
    public Integer getIdKabkot() {
        return idKabkot;
    }

    /**
     *
     * @param idKabkot
     * The id_kabkot
     */
    public void setIdKabkot(Integer idKabkot) {
        this.idKabkot = idKabkot;
    }

    /**
     *
     * @return
     * The idKecematan
     */
    public Integer getIdKecematan() {
        return idKecematan;
    }

    /**
     *
     * @param idKecematan
     * The id_kecematan
     */
    public void setIdKecematan(Integer idKecematan) {
        this.idKecematan = idKecematan;
    }

    /**
     *
     * @return
     * The idKelurahan
     */
    public Integer getIdKelurahan() {
        return idKelurahan;
    }

    /**
     *
     * @param idKelurahan
     * The id_kelurahan
     */
    public void setIdKelurahan(Integer idKelurahan) {
        this.idKelurahan = idKelurahan;
    }

    /**
     *
     * @return
     * The idPdam
     */
    public Integer getIdPdam() {
        return idPdam;
    }

    /**
     *
     * @param idPdam
     * The id_pdam
     */
    public void setIdPdam(Integer idPdam) {
        this.idPdam = idPdam;
    }

    /**
     *
     * @return
     * The kdPelanggan
     */
    public String getKdPelanggan() {
        return kdPelanggan;
    }

    /**
     *
     * @param kdPelanggan
     * The kd_pelanggan
     */
    public void setKdPelanggan(String kdPelanggan) {
        this.kdPelanggan = kdPelanggan;
    }

    /**
     *
     * @return
     * The kdKonversi
     */
    public String getKdKonversi() {
        return kdKonversi;
    }

    /**
     *
     * @param kdKonversi
     * The kd_konversi
     */
    public void setKdKonversi(String kdKonversi) {
        this.kdKonversi = kdKonversi;
    }

    /**
     *
     * @return
     * The kdPelangganOld
     */
    public String getKdPelangganOld() {
        return kdPelangganOld;
    }

    /**
     *
     * @param kdPelangganOld
     * The kd_pelanggan_old
     */
    public void setKdPelangganOld(String kdPelangganOld) {
        this.kdPelangganOld = kdPelangganOld;
    }

    /**
     *
     * @return
     * The namaPelanggan
     */
    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    /**
     *
     * @param namaPelanggan
     * The nama_pelanggan
     */
    public void setNamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }

    /**
     *
     * @return
     * The noKtp
     */
    public String getNoKtp() {
        return noKtp;
    }

    /**
     *
     * @param noKtp
     * The no_ktp
     */
    public void setNoKtp(String noKtp) {
        this.noKtp = noKtp;
    }

    /**
     *
     * @return
     * The alamat
     */
    public String getAlamat() {
        return alamat;
    }

    /**
     *
     * @param alamat
     * The alamat
     */
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    /**
     *
     * @return
     * The tglLahir
     */
    public String getTglLahir() {
        return tglLahir;
    }

    /**
     *
     * @param tglLahir
     * The tgl_lahir
     */
    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }

    /**
     *
     * @return
     * The propinsi
     */
    public String getPropinsi() {
        return propinsi;
    }

    /**
     *
     * @param propinsi
     * The propinsi
     */
    public void setPropinsi(String propinsi) {
        this.propinsi = propinsi;
    }

    /**
     *
     * @return
     * The kodePos
     */
    public String getKodePos() {
        return kodePos;
    }

    /**
     *
     * @param kodePos
     * The kode_pos
     */
    public void setKodePos(String kodePos) {
        this.kodePos = kodePos;
    }

    /**
     *
     * @return
     * The jenisKelamin
     */
    public String getJenisKelamin() {
        return jenisKelamin;
    }

    /**
     *
     * @param jenisKelamin
     * The jenis_kelamin
     */
    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    /**
     *
     * @return
     * The email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     * The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     * The photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     *
     * @param photo
     * The photo
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     *
     * @return
     * The tglDaftar
     */
    public String getTglDaftar() {
        return tglDaftar;
    }

    /**
     *
     * @param tglDaftar
     * The tgl_daftar
     */
    public void setTglDaftar(String tglDaftar) {
        this.tglDaftar = tglDaftar;
    }

    /**
     *
     * @return
     * The tglPenutupan
     */
    public String getTglPenutupan() {
        return tglPenutupan;
    }

    /**
     *
     * @param tglPenutupan
     * The tgl_penutupan
     */
    public void setTglPenutupan(String tglPenutupan) {
        this.tglPenutupan = tglPenutupan;
    }

    /**
     *
     * @return
     * The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The statusKawin
     */
    public String getStatusKawin() {
        return statusKawin;
    }

    /**
     *
     * @param statusKawin
     * The status_kawin
     */
    public void setStatusKawin(String statusKawin) {
        this.statusKawin = statusKawin;
    }

    /**
     *
     * @return
     * The agama
     */
    public String getAgama() {
        return agama;
    }

    /**
     *
     * @param agama
     * The agama
     */
    public void setAgama(String agama) {
        this.agama = agama;
    }

    /**
     *
     * @return
     * The pekerjaan
     */
    public String getPekerjaan() {
        return pekerjaan;
    }

    /**
     *
     * @param pekerjaan
     * The pekerjaan
     */
    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    /**
     *
     * @return
     * The idProv
     */
    public Integer getIdProv() {
        return idProv;
    }

    /**
     *
     * @param idProv
     * The id_prov
     */
    public void setIdProv(Integer idProv) {
        this.idProv = idProv;
    }

    /**
     *
     * @return
     * The telp
     */
    public String getTelp() {
        return telp;
    }

    /**
     *
     * @param telp
     * The telp
     */
    public void setTelp(String telp) {
        this.telp = telp;
    }

    /**
     *
     * @return
     * The hp
     */
    public String getHp() {
        return hp;
    }

    /**
     *
     * @param hp
     * The hp
     */
    public void setHp(String hp) {
        this.hp = hp;
    }

    /**
     *
     * @return
     * The kdType
     */
    public String getKdType() {
        return kdType;
    }

    /**
     *
     * @param kdType
     * The kd_type
     */
    public void setKdType(String kdType) {
        this.kdType = kdType;
    }

    /**
     *
     * @return
     * The kdCabang
     */
    public String getKdCabang() {
        return kdCabang;
    }

    /**
     *
     * @param kdCabang
     * The kd_cabang
     */
    public void setKdCabang(String kdCabang) {
        this.kdCabang = kdCabang;
    }

    /**
     *
     * @return
     * The kdWilayah
     */
    public String getKdWilayah() {
        return kdWilayah;
    }

    /**
     *
     * @param kdWilayah
     * The kd_wilayah
     */
    public void setKdWilayah(String kdWilayah) {
        this.kdWilayah = kdWilayah;
    }

    /**
     *
     * @return
     * The kdBlok
     */
    public String getKdBlok() {
        return kdBlok;
    }

    /**
     *
     * @param kdBlok
     * The kd_blok
     */
    public void setKdBlok(String kdBlok) {
        this.kdBlok = kdBlok;
    }

    /**
     *
     * @return
     * The idZonaBaca
     */
    public Integer getIdZonaBaca() {
        return idZonaBaca;
    }

    /**
     *
     * @param idZonaBaca
     * The id_zona_baca
     */
    public void setIdZonaBaca(Integer idZonaBaca) {
        this.idZonaBaca = idZonaBaca;
    }

    /**
     *
     * @return
     * The idZonaPipa
     */
    public Integer getIdZonaPipa() {
        return idZonaPipa;
    }

    /**
     *
     * @param idZonaPipa
     * The id_zona_pipa
     */
    public void setIdZonaPipa(Integer idZonaPipa) {
        this.idZonaPipa = idZonaPipa;
    }

    public JSONObject toJSON()
    {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id_pelanggan",getIdPelanggan());
            jsonObject.put("lon",getLon());
            jsonObject.put("lat",getLat());
            jsonObject.put("kd_pelanggan",getKdPelanggan());
            jsonObject.put("nama_pelanggan",getNamaPelanggan());
            jsonObject.put("alamat",getAlamat());
            jsonObject.put("tgl_lahir",getTglLahir());
            jsonObject.put("kode_pos",getKodePos());
            jsonObject.put("jenis_kelamin",getJenisKelamin());
            jsonObject.put("email",getEmail());
            jsonObject.put("tgl_daftar",getTglDaftar());
            jsonObject.put("status",getStatus());
            jsonObject.put("status_kawin",getStatusKawin());
            jsonObject.put("agama",getAgama());
            jsonObject.put("telp",getTelp());
            jsonObject.put("hp",getHp());

            return  jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }


}
