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

    private float amount;

    public NoteEntity() {

    }

    @Override
    public String toString() {
        return String.format("%s - %s - %s", name, desc, amount);
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

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.desc);
        dest.writeFloat(this.amount);
    }

    public NoteEntity(Parcel in) {
        this.id = in.readInt();
        this.desc = in.readString();
        this.amount = in.readFloat();
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
