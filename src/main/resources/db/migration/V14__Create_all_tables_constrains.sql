-- appointment
alter table appointment
  add constraint appointment_customer__fk
    foreign key (customer_id) references customer (id);

alter table appointment
  add constraint appointment_pet__fk
    foreign key (pet_id) references pet (id);

alter table appointment
  add constraint appointment_doctor__fk
    foreign key (doctor_id) references doctor (id);

alter table appointment
  add constraint appointment_disease__fk
    foreign key (disease_id) references disease (id);

-- bill
alter table bill
  add constraint bill_customer__fk
    foreign key (customer_id) references customer (id);

-- doctor
alter table doctor
  add constraint doctor_customer__fk
    foreign key (customer_id) references customer (id);

-- instance
alter table instance
  add constraint instance_customer__fk
    foreign key (customer_id) references customer (id);

alter table instance
  add constraint instance_pet__fk
    foreign key (pet_id) references pet (id);

alter table instance
  add constraint instance_doctor__fk
    foreign key (doctor_id) references doctor (id);

alter table instance
  add constraint instance_disease__fk
    foreign key (disease_id) references disease (id);

-- packet
alter table packet
  add constraint packet_customer__fk
    foreign key (customer_id) references customer (id);

-- trolley
alter table trolley
  add constraint trolley_customer__fk
    foreign key (customer_id) references customer (id);

alter table trolley
  add constraint trolley_medicine__fk
    foreign key (medicine_id) references medicine (id);