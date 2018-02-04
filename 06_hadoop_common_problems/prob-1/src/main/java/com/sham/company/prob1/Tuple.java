package com.sham.company.prob1;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


public class Tuple implements WritableComparable<Tuple> {
    private FloatWritable weight;
    private Text occupation;

    private void set(FloatWritable weight, Text occupation) {
        this.weight = weight;
        this.occupation = occupation;
    }

    // hadoop helper methods
    public Tuple() {
        set(new FloatWritable(), new Text());
    }

    public Tuple(Float weight, String occupation) {
        set(new FloatWritable(weight), new Text(occupation));
    }

    public Tuple(FloatWritable weight, Text occupation) {
        set(weight, occupation);
    }

    public FloatWritable getWeight() {
        return this.weight;
    }

    public Text getOccupation() {
        return this.occupation;
    }

    @Override
    public void write(DataOutput output) throws IOException {
        this.weight.write(output);
        this.occupation.write(output);
    }

    @Override
    public void readFields(DataInput input) throws IOException {
        this.weight.readFields(input);
        this.occupation.readFields(input);
    }

    @Override
    public int compareTo(Tuple other) {
        return this.occupation.compareTo(other.occupation);
    }

    @Override
    public int hashCode() {
        return this.occupation.hashCode() ^ this.weight.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Tuple && this.occupation.equals(((Tuple) other).occupation) && this.weight.equals(((Tuple) other).weight);
    }
}