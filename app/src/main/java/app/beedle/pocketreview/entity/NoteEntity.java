package app.beedle.pocketreview.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity
public class NoteEntity implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private String desc;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    private String detail;

    private String amount;

    public NoteEntity() {

    }

    /*
    * '[eNm]' it is sign of End Name
    * '[eNd]' it is sign of End Description
    * '[eNde]' it is sign of End Detail
    * '[eNam]' it is sign of End Amount
    * But not confirm safety if user type this code it could be bug :( 55555555
    * */
    @Override
    public String toString() {
        return String.format("%s'[eNm]'%s'[eNd]'%s'[eNde]'%s'[eNam]'", name, desc, detail, amount);
    }

    public String getNameAll() {
        return String.format("%s\n", name);
    }

    public String getDesctiptionAll() {
        return String.format("%s\n", desc);
    }

    public String getDetailAll() {
        return String.format("%s\n", detail);
    }

    public String getAmountAll() {
        return String.format("%s\n", amount);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.desc);
        dest.writeString(this.detail);
        dest.writeString(this.amount);
    }

    public NoteEntity(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.desc = in.readString();
        this.detail = in.readString();
        this.amount = in.readString();
    }

    public static final Parcelable.Creator<NoteEntity> CREATOR = new Parcelable.Creator<NoteEntity>() {
        @Override
        public NoteEntity createFromParcel(Parcel source) {
            return new NoteEntity(source);
        }

        @Override
        public NoteEntity[] newArray(int size) {
            return new NoteEntity[size];
        }
    };

}
